package com.redck.restaurantmsbff.web_rest;


import com.redck.restaurantmsbff.service.UserService;
import com.redck.restaurantmsbff.service.mapper.UserMapper;
import com.redck.restaurantmsbff.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController implements ApiController
{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static final String USER_USER_ID = "/user/{userId}";
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Constructor for User Controller.
     * @param userService User Service.
     * @param userMapper  User mapper.
     */
    public UserController(final UserService userService, final UserMapper userMapper)
    {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Controller to get a user by id.
     *
     * @param userId id of user to get.
     * @return UserDTO with the provided id.
     */

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = USER_USER_ID,
            produces = {"application/json"})
    public ResponseEntity<UserDTO> getUser(@PathVariable final String userId) throws IOException
    {
        logger.info("Fetching user with ID: {}", userId);

        ResponseEntity<UserDTO> responseEntity = ResponseEntity.ok(userMapper.mapUserToUserDTO(userService.getUser(userId)));

        UserDTO userDTO = responseEntity.getBody();

        logger.info("User fetched successfully with ID: {}. User content: {}", userId, responseEntity);

        return responseEntity;
    }

    /**
     * Controller to edit a user by id.
     *
     * @param userId id of user to edit.
     * @param userDTO user info to update.
     * @return User with user info edited.
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping(value = USER_USER_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<UserDTO> editUser(@PathVariable final String userId, @RequestBody final UserDTO userDTO)
    {
        return ResponseEntity.ok(userMapper.mapUserToUserDTO(userService
                .editUser(userId,userMapper.mapUserDTOToUser(userDTO))));
    }

    /**
     * Controller to delete a user by id.
     *
     * @param userId id of user to delete.
     * @return String with username deleted.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = USER_USER_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<String> deleteUser(@PathVariable final String userId)
    {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
