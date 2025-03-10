package com.redck.restaurantmsbff.client;

import com.redck.restaurantmsbff.config.ApplicationProperties;
import com.redck.restaurantmsbff.domain.MenuItem;
import com.redck.restaurantmsbff.domain.Restaurant;
import com.redck.restaurantmsbff.domain.Schedule;
import com.redck.restaurantmsbff.logging.Logger;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.service.mapper.MenuItemMapper;
import com.redck.restaurantmsbff.service.mapper.RestaurantMapper;
import com.redck.restaurantmsbff.service.mapper.ScheduleMapper;
import com.redck.restaurantmsbff.service.model.MenuItemDTO;
import com.redck.restaurantmsbff.service.model.RestaurantDTO;
import com.redck.restaurantmsbff.service.model.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class RestaurantClient
{
    private static final Logger logger = new Logger(RestaurantClient.class);

    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;
    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;
    private final ScheduleMapper scheduleMapper;

    public static final String RESTAURANT_RESTAURANT_ID = "/api/restaurant/{restaurantUid}";
    public static final String RESTAURANT = "/api/restaurant";
    private static final String RESTAURANT_MENU = "/api/restaurant/{restaurantUid}/menu";
    private static final String RESTAURANT_MENU_ITEM = "/api/restaurant/{restaurantUid}/menu/{menuItemName}";
    private static final String RESTAURANT_SCHEDULE = "/api/restaurant/{restaurantUid}/schedule";
    private static final String RESTAURANT_SCHEDULE_DAY = "/api/restaurant/{restaurantUid}/schedule/{day}";


    @Autowired
    public RestaurantClient(final ApplicationProperties applicationProperties, RestTemplate restTemplate, RestaurantMapper restaurantMapper, MenuItemMapper menuItemMapper, ScheduleMapper scheduleMapper)
    {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
        this.restaurantMapper = restaurantMapper;
        this.menuItemMapper = menuItemMapper;
        this.scheduleMapper = scheduleMapper;
    }

    /**
     * Client to get Restaurant by uid.
     * @param restaurantUid restaurant uid.
     * @return restaurant.
     */
    public Restaurant getRestaurant(final String restaurantUid)
    {
        System.out.println("RESTAURANTUID: " + restaurantUid);
        final String url = applicationProperties.getMsManager().getUrl().concat(RESTAURANT_RESTAURANT_ID);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetRestaurantId = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.RETRIEVED),
                "Get Restaurant by id: " + restaurantUid);

        try
        {
            final ResponseEntity<RestaurantDTO> responseRestaurant = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetRestaurantId,
                    new ParameterizedTypeReference<RestaurantDTO>() {
                    }, params);

            if(HttpStatus.OK.equals(responseRestaurant.getStatusCode()))
            {
                restaurantDTO = responseRestaurant.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.RETRIEVED),
                    "Restaurant uid not found");
        }

        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
    }

    /**
     * Client to create restaurant.
     * @param restaurant restaurant.
     * @return restaurant.
     */
    public Restaurant createRestaurant(final Restaurant restaurant)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final RestaurantDTO requestRestaurantDTO = restaurantMapper.mapRestaurantToRestaurantDTO(restaurant);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<RestaurantDTO> requestCreateRestaurantClient = new HttpEntity<>(requestRestaurantDTO ,headers);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.PERSISTED),
                "Create Restaurant: " + restaurant.toString());

        try
        {
            final ResponseEntity<RestaurantDTO> responseRestaurantCreated = restTemplate.exchange(url, HttpMethod.POST,
                    requestCreateRestaurantClient, RestaurantDTO.class);
            if(HttpStatus.OK.equals(responseRestaurantCreated.getStatusCode()))
            {
                restaurantDTO = responseRestaurantCreated.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.PERSISTED)
                    , "Restaurant was not created!!");
        }

        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
    }

    public Restaurant addMenuItem(final String restaurantUid, final MenuItem menuItem)
    {
        final String url = applicationProperties.getMsManager().getUrl().concat(RESTAURANT_MENU);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MenuItem> requestAddMenuItem = new HttpEntity<>(menuItem, headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.MENU, LogTag.PERSISTED),
                "Adding menu item to restaurant: " + restaurantUid);

        try
        {
            final ResponseEntity<RestaurantDTO> response = restTemplate.exchange(url, HttpMethod.POST,
                    requestAddMenuItem, RestaurantDTO.class, params);

            if (HttpStatus.OK.equals(response.getStatusCode()))
            {
                restaurantDTO = response.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.MENU, LogTag.PERSISTED),
                    "Failed to add menu item to restaurant: " + restaurantUid);
        }

        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);

    }

    public Restaurant addSchedule(final String restaurantUid, final Schedule schedule)
    {
        final String url = applicationProperties.getMsManager().getUrl().concat(RESTAURANT_SCHEDULE);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Schedule> requestAddSchedule = new HttpEntity<>(schedule, headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.SCHEDULE, LogTag.PERSISTED),
                "Adding schedule to restaurant: " + restaurantUid);

        try
        {
            final ResponseEntity<RestaurantDTO> response = restTemplate.exchange(url, HttpMethod.POST,
                    requestAddSchedule, RestaurantDTO.class, params);

            if (HttpStatus.OK.equals(response.getStatusCode()))
            {
                restaurantDTO = response.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.SCHEDULE, LogTag.PERSISTED),
                    "Failed to add schedule to restaurant: " + restaurantUid);
        }

        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);

    }

    /**
     * Client to edit restaurant.
     * @param restaurantUid restaurant id.
     * @param restaurant restaurant.
     * @return restaurant.
     */
    public Restaurant editRestaurant(final String restaurantUid, final Restaurant restaurant)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT_RESTAURANT_ID);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final RestaurantDTO requestRestaurantDTO = restaurantMapper.mapRestaurantToRestaurantDTO(restaurant);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<RestaurantDTO> requestEditRestaurantClient = new HttpEntity<>(requestRestaurantDTO ,headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.EDITED),
                "Edit Restaurant by id " + restaurantUid);

        try
        {
            final ResponseEntity<RestaurantDTO> responseRestaurantEdited = restTemplate.exchange(url, HttpMethod.PUT,
                    requestEditRestaurantClient, RestaurantDTO.class, params);
            if(HttpStatus.OK.equals(responseRestaurantEdited.getStatusCode()))
            {
                restaurantDTO = responseRestaurantEdited.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.EDITED)
                    , "Restaurant was not edited!!");
        }

        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
    }

    /**
     * Client to delete restaurant.
     * @param restaurantUid restaurant id.
     * @return restaurant deleted.
     */
    public Restaurant deleteRestaurant(final String restaurantUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT_RESTAURANT_ID);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetRestaurantId = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.DELETED),
                "Delete Restaurant by id: " + restaurantUid);

        try
        {
            final ResponseEntity<RestaurantDTO> responseRestaurant = restTemplate.exchange(url, HttpMethod.DELETE,
                    requestGetRestaurantId,
                    new ParameterizedTypeReference<RestaurantDTO>() {
                    }, params);
            if(HttpStatus.OK.equals(responseRestaurant.getStatusCode()))
            {
                restaurantDTO = responseRestaurant.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.DELETED),
                    "Restaurant id not found");
        }

        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
    }

    public Restaurant deleteMenuItem(final String restaurantUid, final String menuItemName)
    {
        final String url = applicationProperties.getMsManager().getUrl().concat(RESTAURANT_MENU_ITEM);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestDeleteMenuItem = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);
        params.put("menuItemName", menuItemName);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.DELETED),
                "Delete Menu Item: " + menuItemName + " from Restaurant: " + restaurantUid);

        try
        {
            // Perform the DELETE request
            final ResponseEntity<RestaurantDTO> responseRestaurantDeleted = restTemplate.exchange(url, HttpMethod.DELETE,
                    requestDeleteMenuItem, RestaurantDTO.class, params);

            // If the status is OK, map the response to the restaurant DTO
            if (HttpStatus.OK.equals(responseRestaurantDeleted.getStatusCode()))
            {
                restaurantDTO = responseRestaurantDeleted.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            // Handle failure and log the error
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.DELETED),
                    "Menu Item: " + menuItemName + " not found in Restaurant: " + restaurantUid);
        }

        // Map and return the updated restaurant after menu item deletion
        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);

    }

    public Restaurant deleteSchedule(final String restaurantUid, final String day)
    {
        final String url = applicationProperties.getMsManager().getUrl().concat(RESTAURANT_SCHEDULE_DAY);
        final String correlationId = UUID.randomUUID().toString();
        RestaurantDTO restaurantDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestDeleteSchedule = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);
        params.put("day", day);


        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.DELETED),
                "Delete Schedule: " + day + " from Restaurant: " + restaurantUid);

        try
        {
            // Perform the DELETE request
            final ResponseEntity<RestaurantDTO> responseRestaurantDeleted = restTemplate.exchange(url, HttpMethod.DELETE,
                    requestDeleteSchedule, RestaurantDTO.class, params);

            // If the status is OK, map the response to the restaurant DTO
            if (HttpStatus.OK.equals(responseRestaurantDeleted.getStatusCode()))
            {
                restaurantDTO = responseRestaurantDeleted.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            // Handle failure and log the error
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.DELETED),
                    "Schedule: " + day + " not found in Restaurant: " + restaurantUid);
        }

        // Map and return the updated restaurant after menu item deletion
        return restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);

    }

    /**
     * Client to get all restaurants.
     * @return restaurants list.
     */
    public List<Restaurant> getAllRestaurants()
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT);
        final String correlationId = UUID.randomUUID().toString();
        List<RestaurantDTO> restaurantDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity requestGetRestaurantId = new HttpEntity<>(headers);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.RETRIEVED),
                "Get All Restaurants list");

        try
        {
            final ResponseEntity<List<RestaurantDTO>> responseRestaurant = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetRestaurantId,
                    new ParameterizedTypeReference<List<RestaurantDTO>>() {
                    });
            if(HttpStatus.OK.equals(responseRestaurant.getStatusCode()))
            {
                restaurantDTOList = responseRestaurant.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.RESTAURANTS, LogTag.RETRIEVED),
                    "Restaurant list not found");
        }

        return restaurantMapper.mapRestaurantDTOListToRestaurantList(restaurantDTOList);
    }

    public List<MenuItem> getMenu(final String restaurantUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT_MENU);
        final String correlationId = UUID.randomUUID().toString();
        List<MenuItemDTO> menuItemDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestGetMenu = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.MENU, LogTag.RETRIEVED),
                "Get All Menu list");

        try
        {
            final ResponseEntity<List<MenuItemDTO>> responseMenu = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetMenu, new ParameterizedTypeReference<List<MenuItemDTO>>() {
                    }, params);

            // If the status is OK, map the response to the restaurant DTO
            if (HttpStatus.OK.equals(responseMenu.getStatusCode()))
            {
                menuItemDTOList = responseMenu.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.MENU, LogTag.RETRIEVED),
                    "Menu list not found");
        }

        return menuItemMapper.mapMenuItemDTOListToMenuItemList(menuItemDTOList);
    }

    public MenuItem getMenuItem(final String restaurantUid, final String menuItemName)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT_MENU_ITEM);
        final String correlationId = UUID.randomUUID().toString();
        MenuItemDTO menuItemDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestGetMenuItem = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);
        params.put("menuItemName", menuItemName);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.MENU, LogTag.RETRIEVED),
                "Get Restaurant by id: " + restaurantUid + " with the menu item: " + menuItemName);

        try
        {
            final ResponseEntity<MenuItemDTO> responseMenuItem = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetMenuItem, new ParameterizedTypeReference<MenuItemDTO>() {
                    }, params);

            // If the status is OK, map the response to the restaurant DTO
            if (HttpStatus.OK.equals(responseMenuItem.getStatusCode()))
            {
                menuItemDTO = responseMenuItem.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.MENU, LogTag.RETRIEVED),
                    "Menu Item not found");
        }

        return menuItemMapper.mapMenuItemDTOToMenuItem(menuItemDTO);
    }

    public List<Schedule> getSchedule(final String restaurantUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT_SCHEDULE);
        final String correlationId = UUID.randomUUID().toString();
        List<ScheduleDTO> scheduleDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestGetSchedule = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.SCHEDULE, LogTag.RETRIEVED),
                "Get All Schedule list");

        try
        {
            final ResponseEntity<List<ScheduleDTO>> responseSchedule = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetSchedule, new ParameterizedTypeReference<List<ScheduleDTO>>() {
                    }, params);

            // If the status is OK, map the response to the restaurant DTO
            if (HttpStatus.OK.equals(responseSchedule.getStatusCode()))
            {
                scheduleDTOList = responseSchedule.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.SCHEDULE, LogTag.RETRIEVED),
                    "Schedule list not found");
        }

        return scheduleMapper.mapScheduleDTOListToScheduleList(scheduleDTOList);
    }

    public Schedule getScheduleByDay(final String restaurantUid, final String day)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(RESTAURANT_SCHEDULE_DAY);
        final String correlationId = UUID.randomUUID().toString();
        ScheduleDTO scheduleDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestGetScheduleDay = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);
        params.put("day", day);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.SCHEDULE, LogTag.RETRIEVED),
                "Get Restaurant by id: " + restaurantUid + " with schedule from day: " + day);

        try
        {
            final ResponseEntity<ScheduleDTO> responseScheduleDay = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetScheduleDay, new ParameterizedTypeReference<ScheduleDTO>() {
                    }, params);

            // If the status is OK, map the response to the restaurant DTO
            if (HttpStatus.OK.equals(responseScheduleDay.getStatusCode()))
            {
                scheduleDTO = responseScheduleDay.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.SCHEDULE, LogTag.RETRIEVED),
                    "Schedule day not found");
        }

        return scheduleMapper.mapScheduleDTOToSchedule(scheduleDTO);
    }


}
