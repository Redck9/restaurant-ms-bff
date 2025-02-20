package com.redck.restaurantmsbff.web_rest;

import com.redck.restaurantmsbff.domain.Restaurant;
import com.redck.restaurantmsbff.service.RestaurantService;
import com.redck.restaurantmsbff.service.mapper.RestaurantMapper;
import com.redck.restaurantmsbff.service.model.RestaurantDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController implements ApiController
{

    public static final String RESTAURANT_RESTAURANT_ID = "/restaurant/{restaurantUid}";
    public static final String RESTAURANT = "/restaurant";

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    /**
     * Constructor for Restaurant Controller.
     *  @param restaurantService Restaurant Service.
     * @param restaurantMapper  Restaurant Mapper.
     */
    public RestaurantController(final RestaurantService restaurantService, final RestaurantMapper restaurantMapper)
    {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }


    /**
     * Controller to get a restaurantDTO by uid
     * @param restaurantUid restaurantDTO uid to get
     * @return RestaurantDTO with the provided uid
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get a restaurant by its id")
    @GetMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"})
    ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable final String restaurantUid)
    {

        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.getRestaurant(restaurantUid)));
    }

    /**
     * Controller to create a restaurantDTO
     * @param restaurantDTO restaurantDTO to create
     * @return RestaurantDTO created
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping(value = RESTAURANT,
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody final RestaurantDTO restaurantDTO)
    {

        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.createRestaurant(restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO))));
    }

    /**
     * Controller to edit a restaurantDTO by uid
     * @param restaurantUid restaurantDTO uid to be edited
     * @param restaurantToEdit restaurantDTO update
     * @return RestaurantDTO edited
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<RestaurantDTO> editRestaurant(@PathVariable final String restaurantUid, @RequestBody final RestaurantDTO restaurantToEdit)
    {

        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.editRestaurant(restaurantUid, restaurantMapper.mapRestaurantDTOToRestaurant(restaurantToEdit))));
    }


    /**
     * Controller to delete restaurantDTO by uid
     * @param restaurantUid restaurantDTO uid to be deleted
     * @return RestaurantDTO deleted
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"})
    ResponseEntity<RestaurantDTO> deleteRestaurant(@PathVariable final String restaurantUid)
    {

        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.deleteRestaurant(restaurantUid)));
    }

    /**
     * Controller to get a list with all restaurantsDTO
     * @return restaurantsDTO list
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = RESTAURANT,
            produces = {"application/json"})
    ResponseEntity<List<RestaurantDTO>> getAllRestaurants()
    {

        return ResponseEntity.ok(restaurantMapper.mapRestaurantListToRestaurantDTOList(
                restaurantService.getAllRestaurants()));
    }
}
