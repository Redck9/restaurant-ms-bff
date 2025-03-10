package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.logging.UserNotFoundException;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService
{

    private static final String DEFAULT_PICTURE = "empty";
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(final ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }


    /**
     * Create User.
     * @param client user.
     * @return User created.
     */
    public Client createUser(final Client client)
    {
        //final Optional<Client> userOptional = clientRepository.findByUid(client.getUid());
        final Optional<Client> userOptional = clientRepository.findByUsername(client.getUsername());
        final Optional<Client> userByEmail = clientRepository.findByEmail(client.getEmail());

        //System.out.println("USER: " + username);
        System.out.println("USER: " + userOptional);
        //userOptional.isEmpty()
        if(userOptional.isEmpty() && userByEmail.isEmpty())
        {
            logger.info(MDC.get("correlationId"), Arrays.asList(LogTag.USERS, LogTag.PERSISTED),
                    "Create User: " + client.toString());

            client.uid(UUID.randomUUID().toString());
            client.setPassword(client.getPassword());
            final Client clientBuild = buildUserInfo(client);
            System.out.println("USER REGISTERED: " + clientBuild.toString());
            return clientRepository.save(clientBuild);

        }
        throw new NullPointerException("User already exists!!");

    }

    private Client buildUserInfo(final Client client)
    {
        System.out.println("PICTURE AND NAME ARE EMPTY");

        if(client.getPicture() == null)
        {
            client.picture(DEFAULT_PICTURE);
        }

        if(client.getName() == null)
        {
            client.name(client.getUsername());
        }

        if(client.getRole() == null)
        {
            client.role(DEFAULT_ROLE);
        }

        if(client.getFavoriteRestaurants() == null)
        {
            client.favoriteRestaurants(new ArrayList<>());
        }

        return client;
    }

    /**
     * Get User by user Id.
     * @param userId user Id.
     * @return User object.
     */
    public Client getUser(final String userId)
    {
        final Optional<Client> userOptional = getUserById(userId, "User does not exists!!");

        logger.info(MDC.get("correlationId"), Arrays.asList(LogTag.USERS, LogTag.RETRIEVED),
                "Get User by id:" + userId + ": " + userOptional.get().toString());

        return userOptional.get();

    }

    /**
     * Edit user by id.
     * @param userId user id to edit.
     * @param clientToEdit new user data.
     * @return User with user info edited.
     */
    public Client editUser(final String userId, final Client clientToEdit)
    {
        final Optional<Client> userOptional = getUserById(userId, "User to be edited does not exists!!");

        logger.info(MDC.get("correlationId"),  Arrays.asList(LogTag.USERS, LogTag.EDITED),
                "Edit User by id " + userId);

        // Check and update each field only if it's not null in the clientToEdit
        if(clientToEdit.getUsername() == null)
        {
            logger.debug("Didn't want to change the username");
            clientToEdit.username(userOptional.get().getUsername());
        }

        if(clientToEdit.getEmail() == null)
        {
            logger.debug("Didn't want to change the email");
            clientToEdit.email(userOptional.get().getEmail());
        }

        if(clientToEdit.getPassword() == null)
        {
            logger.debug("Didn't want to change the password");
            clientToEdit.password(userOptional.get().getPassword());
        }

        if(clientToEdit.getRole() == null)
        {
            logger.debug("Didn't want to change the role");
            clientToEdit.role(userOptional.get().getRole());
        }

        if(clientToEdit.getName() == null)
        {
            logger.debug("Didn't want to change the name");
            clientToEdit.name(userOptional.get().getName());
        }

        if(clientToEdit.getPicture() == null)
        {
            logger.debug("Didn't want to change the picture");
            clientToEdit.picture(userOptional.get().getPicture());
        }

        userOptional.get()
                .username(clientToEdit.getUsername())
                .role(clientToEdit.getRole())
                .email(clientToEdit.getEmail())
                .password(clientToEdit.getPassword())
                .name(clientToEdit.getName())
                .picture(clientToEdit.getPicture());

        return clientRepository.save(userOptional.get());
    }

    public void updateRefreshToken(String username, String refreshToken)
    {
        Optional<Client> userOptional = clientRepository.findByUsername(username);
        if(!userOptional.isEmpty())
        {
            Client user = userOptional.get();
            System.out.println("🔹 Found user: " + user);
            System.out.println("🔹 Updating refresh token to: " + refreshToken);

            if (refreshToken != null && !refreshToken.isEmpty()) {
                user.setRefreshToken(refreshToken);
                System.out.println("USER AFTER LOG IN: " + user);
                clientRepository.save(user);
                logger.info("Successfully updated refresh token for user: {}", username);
            } else {
                logger.warn("Received null or empty refresh token for user: {}", username);
            }
        }
        else
        {
            throw new UserNotFoundException("User not found: " + username);
        }
    }

    /**
     * Delete user by id.
     * @param userId user id to delete.
     * @return username of the deleted user.
     */
    public String deleteUser(final String userId)
    {
        final Optional<Client> userOptional = getUserById(userId, "User to be deleted does not exists!!");

        clientRepository.delete(userOptional.get());

        logger.info(MDC.get("correlationId"), Arrays.asList(LogTag.USERS, LogTag.DELETED),
                "Delete User by id: " + userId);

        return "User " + userOptional.get().getUsername() + " was deleted";
    }

    public Client getUserByUsername(final String username)
    {
        final Optional<Client> userOptional = clientRepository.findByUsername(username);

        if(userOptional.isEmpty())
        {
            throw new NullPointerException("No user found");
        }
        return userOptional.get();
    }

    /**
     * Find User on Repository
     * @param userId user Id.
     * @param exceptionMessage exception Message
     * @return Optional of User
     */
    private Optional<Client> getUserById(final String userId, final String exceptionMessage)
    {
        final Optional<Client> userOptional = clientRepository.findByUid(userId);

        if(userOptional.isEmpty())
        {
            throw new NullPointerException(exceptionMessage);
        }
        return userOptional;
    }

    /**
     * Add favorite restaurant.
     * @param userUid user uid.
     * @param restaurantUid restaurant uid to add to the list
     * @return Client.
     */
    public Client addFavoriteRestaurant(String userUid, String restaurantUid )
    {
        Optional<Client> userOptional = getUserById(userUid, "User to be deleted does not exists!!");

        if(userOptional.isEmpty())
        {
            throw new RuntimeException("User not found!");
        }

        Client client = userOptional.get();
        List<String> favoriteRestaurants = client.getFavoriteRestaurants();
        if(!favoriteRestaurants.contains(restaurantUid))
        {
            favoriteRestaurants.add(restaurantUid);
            client.setFavoriteRestaurants(favoriteRestaurants);
            clientRepository.save(client);
        }

        return client;
    }

    /**
     * Delete favorite restaurant by user uid and restaurant uid.
     * @param userUid user id to get the list of favorite restaurants.
     * @param restaurantUid restaurant uid to remove from favorite restaurants
     * @return client.
     */
    public Client removeFavoriteRestaurant(String userUid, String restaurantUid)
    {
        Optional<Client> userOptional = getUserById(userUid, "User to be deleted does not exists!!");

        if(userOptional.isEmpty())
        {
            throw new RuntimeException("User not found!");
        }

        Client client = userOptional.get();
        List<String> favoriteRestaurants = client.getFavoriteRestaurants();
        if(favoriteRestaurants.contains(restaurantUid))
        {
            favoriteRestaurants.remove(restaurantUid);
            client.setFavoriteRestaurants(favoriteRestaurants);
            clientRepository.save(client);
        }

        return client;
    }

    /**
     * Get list of favorite restaurants.
     * @param userUid user uid.
     * @return List of favorite restaurants.
     */
    public List<String> getFavoriteRestaurants(String userUid)
    {
        Optional<Client> userOptional = getUserById(userUid, "User to be deleted does not exists!!");

        if(userOptional.isEmpty())
        {
            throw new RuntimeException("User not found!!");
        }

        Client client = userOptional.get();
        return client.getFavoriteRestaurants();
    }


}
