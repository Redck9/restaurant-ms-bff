package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService
{

    private static final String DEFAULT_PICTURE = null;
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
        final Optional<Client> userOptional = clientRepository.findByUid(client.getUid());

        if(userOptional.isEmpty())
        {
            logger.info(MDC.get("correlationId"), Arrays.asList(LogTag.USERS, LogTag.PERSISTED),
                    "Create User: " + client.toString());

            client.uid(UUID.randomUUID().toString());
            final Client clientBuild = buildUserInfo(client);
            return clientRepository.save(clientBuild);
        }
        throw new NullPointerException("User already exists!!");
    }

    private Client buildUserInfo(final Client client)
    {
        if(client.getPicture().isEmpty())
        {
            client.picture(DEFAULT_PICTURE);
        }

        if(client.getName().isEmpty())
        {
            client.name(client.getUsername());
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

        userOptional.get()
                .username(clientToEdit.getUsername())
                .email(clientToEdit.getEmail())
                .password(clientToEdit.getPassword())
                .name(clientToEdit.getName())
                .picture(clientToEdit.getPicture());

        return clientRepository.save(userOptional.get());
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

        return "User " + userOptional.get().getUsername() + "was deleted";
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


}
