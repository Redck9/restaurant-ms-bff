package com.redck.restaurantmsbff.web_rest;


import com.redck.restaurantmsbff.config.JwtTokenProvider;
import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.logging.UserNotFoundException;
import com.redck.restaurantmsbff.config.CustomAuthenticationProvider;
import com.redck.restaurantmsbff.service.ClientService;
import com.redck.restaurantmsbff.service.mapper.ClientMapper;
import com.redck.restaurantmsbff.service.model.ClientDTO;
import com.redck.restaurantmsbff.service.model.LoginRequestDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ClientController implements ApiController
{

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    public static final String USER_USER_ID = "/user/{userId}";
    public static final String USER_USER_USERNAME = "/user/{username}";
    public static final String USER_FAVORITE_RESTAURANT = "/user/{userId}/favorites/{restaurantUid}";
    public static final String USER_FAVORITE_RESTAURANTS = "/user/{userId}/favorites";
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    /**
     * Constructor for User Controller.
     * @param clientService User Service.
     * @param clientMapper  User mapper.
     */
    public ClientController(final CustomAuthenticationProvider customAuthenticationProvider, final ClientService clientService, final ClientMapper clientMapper, final JwtTokenProvider jwtTokenProvider)
    {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.clientService = clientService;
        this.clientMapper = clientMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Controller to log in a user by its username and password.
     *
     * @param authentication authentication of user to get.
     * @return String with the provided token.
     */
    /*@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final Authentication authentication)
    {
        System.out.println("Username: " + authentication.getName());
        System.out.println("Password: " + authentication.getCredentials().toString());

        String name = authentication.getName();
        String password =  authentication.getCredentials().toString();

        try
        {
            Authentication authenticated = customAuthenticationProvider.authenticate(authentication);

            System.out.println("AUTHENTICATED: " + authenticated);
            logger.info("AUTHENTICATED: {}", authenticated);

            String token = generateToken(authenticated);

            // Authentication successful, return token
            logger.info("AUTHENTICATED: {}", token);
            return ResponseEntity.ok(token);
        }
        catch (UserNotFoundException e)
        {
            // User not found, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        catch (RuntimeException e)
        {
            // Invalid username or password, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }*/

    /**
     * Controller to log in a user by its username and password.
     *
     * @param loginRequest authentication of user to get.
     * @return String with the provided token.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody final LoginRequestDTO loginRequest)
    {
        System.out.println("🔹 Login Attempt - Username/Email: " + loginRequest.getIdentifier());
        System.out.println("🔹 Login Attempt - Password: " + loginRequest.getPassword());


        try
        {
            Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.getIdentifier(), loginRequest.getPassword());

            Authentication authenticated = customAuthenticationProvider.authenticate(authenticationRequest);
            System.out.println("AUTHENTICATED: " + authenticated);
            logger.info("AUTHENTICATED: {}", authenticated);

            Object principal = authenticated.getPrincipal();
            String username;
            String userUid;

            if (principal instanceof Client)
            {
                username = ((Client) principal).getUsername(); // Fix: Get username from Client object
                userUid = ((Client) principal).getUid();
            }
            else
            {
                username = authenticated.getName(); // Default behavior
                Object user = authenticated.getPrincipal();
                if(user instanceof Client)
                {
                    userUid = ((Client) user).getUid();
                }
                else
                {
                    userUid = "";
                }
            }
            System.out.println("NAME!!!!!!!!!: " + username);

            if(authenticated != null)
            {

                SecurityContextHolder.getContext().setAuthentication(authenticated);

                String accessToken = generateToken(username);
                String refreshToken = generateRefreshToken(username);

                System.out.println("ACCESS TOKEN: " + accessToken);
                System.out.println("REFRESH TOKEN: " + refreshToken);

                clientService.updateRefreshToken(username, refreshToken);

                // Authentication successful, return token
                logger.info("ACCESS TOKEN: {}", accessToken);
                logger.info("REFRESH TOKEN: {}", refreshToken);

                Map<String, String> response = new HashMap<>();
                response.put("accessToken", accessToken);
                response.put("refreshToken", refreshToken);
                response.put("userUid", userUid);

                return ResponseEntity.ok(response);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid username or password"));
            }



        }
        catch (UserNotFoundException e)
        {
            // User not found, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not found"));
        }
        catch (RuntimeException e)
        {
            // Invalid username or password, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid username or password"));
        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request)
    {
        String refreshToken = request.get("refreshToken");

        try
        {
            if(!jwtTokenProvider.validateToken(refreshToken))
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid refresh token"));
            }

            String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
            String newAccessToken = jwtTokenProvider.generateToken(username);
            String newRefreshToken = jwtTokenProvider.generateRefreshToken(username);

            clientService.updateRefreshToken(username, newRefreshToken);

            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (ExpiredJwtException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Refresh token expired, please log in again"));
        }

    }

    private String generateRefreshToken(String username)
    {
        return jwtTokenProvider.generateRefreshToken(username);
    }

    private String generateToken(String username)
    {
        return jwtTokenProvider.generateToken(username);
    }

    /**
     * Controller to register a user.
     *
     * @param clientDTO clientDTO to register the user.
     * @return String with a message to let the user know if were successful.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody final ClientDTO  clientDTO) {
        System.out.println("client: " + clientDTO.toString());
        try
        {
            clientService.createUser(clientMapper.mapClientDTOToClient(clientDTO));
            return ResponseEntity.ok("User registered successfully");
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }

    /**
     * Controller to check if user is logged in.
     *
     * @return String with a message to let the user know if is authenticated.
     */
    @GetMapping("/secureEndpoint")
    public ResponseEntity<String> secureEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated())
        {
            // User is authenticated
            return ResponseEntity.ok("Access granted to authenticated user");
        }
        else
        {
            // User is not authenticated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied. Please log in.");
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = USER_USER_ID)
    public ResponseEntity<String> deleteUser(@PathVariable final String userId)
    {
        logger.info("Deleting user with ID: {}", userId);
        return ResponseEntity.ok(clientService.deleteUser(userId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = USER_FAVORITE_RESTAURANT)
    public ResponseEntity<ClientDTO> addFavoriteRestaurant(@PathVariable String userId, @PathVariable String restaurantUid)
    {
        return ResponseEntity.ok(clientMapper.mapClientToClientDTO(clientService.addFavoriteRestaurant(userId, restaurantUid)));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(value = USER_FAVORITE_RESTAURANT)
    public ResponseEntity<ClientDTO> removeFavoriteRestaurant(@PathVariable String userId, @PathVariable String restaurantUid)
    {
        return ResponseEntity.ok(clientMapper.mapClientToClientDTO(clientService.removeFavoriteRestaurant(userId, restaurantUid)));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = USER_FAVORITE_RESTAURANTS)
    public List<String> getFavoriteRestaurants(@PathVariable String userId)
    {
        return clientService.getFavoriteRestaurants(userId);
    }

}
