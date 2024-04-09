package com.redck.restaurantmsbff.web_rest;


import com.redck.restaurantmsbff.logging.UserNotFoundException;
import com.redck.restaurantmsbff.service.AuthenticationService;
import com.redck.restaurantmsbff.service.ClientService;
import com.redck.restaurantmsbff.service.mapper.ClientMapper;
import com.redck.restaurantmsbff.service.model.ClientDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ClientController implements ApiController
{

    private final AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    public static final String USER_USER_ID = "/user/{userId}";
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    /**
     * Constructor for User Controller.
     * @param clientService User Service.
     * @param clientMapper  User mapper.
     */
    public ClientController(final AuthenticationService authenticationService, final ClientService clientService, final ClientMapper clientMapper)
    {
        this.authenticationService = authenticationService;
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response)
    {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try {
            String token = authenticationService.authenticate(username, password);

            // Authentication successful, return token or redirect to home page
            // For token-based authentication, you can return the token
            return ResponseEntity.ok(token);
        } catch (UserNotFoundException e) {
            // User not found, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (RuntimeException e) {
            // Invalid username or password, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody final ClientDTO  clientDTO) {
        try {
            clientService.createUser(clientMapper.mapClientDTOToClient(clientDTO));
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }

    /**
     * Controller to get a user by id.
     *
     * @param userId id of user to get.
     * @return ClientDTO with the provided id.
     */

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = USER_USER_ID,
            produces = {"application/json"})
    public ResponseEntity<ClientDTO> getUser(@PathVariable final String userId) throws IOException
    {
        logger.info("Fetching user with ID: {}", userId);

        ResponseEntity<ClientDTO> responseEntity = ResponseEntity.ok(clientMapper.mapClientToClientDTO(clientService.getUser(userId)));

        ClientDTO clientDTO = responseEntity.getBody();

        logger.info("User fetched successfully with ID: {}. User content: {}", userId, responseEntity);

        return responseEntity;
    }

    /**
     * Controller to edit a user by id.
     *
     * @param userId id of user to edit.
     * @param clientDTO user info to update.
     * @return User with user info edited.
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping(value = USER_USER_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<ClientDTO> editUser(@PathVariable final String userId, @RequestBody final ClientDTO clientDTO)
    {
        return ResponseEntity.ok(clientMapper.mapClientToClientDTO(clientService
                .editUser(userId, clientMapper.mapClientDTOToClient(clientDTO))));
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
        return ResponseEntity.ok(clientService.deleteUser(userId));
    }

}
