package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.domain.User;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{

    private static final String DEFAULT_PICTURE = null;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    /**
     * Create User.
     * @param user user.
     * @return User created.
     */
    public User createUser(final User user)
    {
        final Optional<User> userOptional = userRepository.findByUid(user.getUid());

        if(userOptional.isEmpty())
        {
            logger.info(MDC.get("correlationId"), Arrays.asList(LogTag.USERS, LogTag.PERSISTED),
                    "Create User: " + user.toString());

            user.uid(UUID.randomUUID().toString());
            final User userBuild = buildUserInfo(user);
            return userRepository.save(userBuild);
        }
        throw new NullPointerException("User already exists!!");
    }

    private User buildUserInfo(final User user)
    {
        if(user.getPicture().isEmpty())
        {
            user.picture(DEFAULT_PICTURE);
        }

        if(user.getName().isEmpty())
        {
            user.name(user.getUsername());
        }

        return user;
    }

    /**
     * Get User by user Id.
     * @param userId user Id.
     * @return User object.
     */
    public User getUser(final String userId)
    {
        final Optional<User> userOptional = getUserById(userId, "User does not exists!!");

        logger.info(MDC.get("correlationId"), Arrays.asList(LogTag.USERS, LogTag.RETRIEVED),
                "Get User by id:" + userId + ": " + userOptional.get().toString());

        return userOptional.get();

    }

    /**
     * Edit user by id.
     * @param userId user id to edit.
     * @param userToEdit new user data.
     * @return User with user info edited.
     */
    public User editUser(final String userId, final User userToEdit)
    {
        final Optional<User> userOptional = getUserById(userId, "User to be edited does not exists!!");

        logger.info(MDC.get("correlationId"),  Arrays.asList(LogTag.USERS, LogTag.EDITED),
                "Edit User by id " + userId);

        userOptional.get()
                .username(userToEdit.getUsername())
                .email(userToEdit.getEmail())
                .password(userToEdit.getPassword())
                .name(userToEdit.getName())
                .picture(userToEdit.getPicture());

        return userRepository.save(userOptional.get());
    }

    /**
     * Delete user by id.
     * @param userId user id to delete.
     * @return username of the deleted user.
     */
    public String deleteUser(final String userId)
    {
        final Optional<User> userOptional = getUserById(userId, "User to be deleted does not exists!!");

        userRepository.delete(userOptional.get());

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
    private Optional<User> getUserById(final String userId, final String exceptionMessage)
    {
        final Optional<User> userOptional = userRepository.findByUid(userId);
        if(userOptional.isEmpty())
        {
            throw new NullPointerException(exceptionMessage);
        }
        return userOptional;
    }


}
