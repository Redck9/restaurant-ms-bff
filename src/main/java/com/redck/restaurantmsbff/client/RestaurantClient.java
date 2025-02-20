package com.redck.restaurantmsbff.client;

import com.redck.restaurantmsbff.config.ApplicationProperties;
import com.redck.restaurantmsbff.domain.Restaurant;
import com.redck.restaurantmsbff.logging.Logger;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.service.mapper.RestaurantMapper;
import com.redck.restaurantmsbff.service.model.RestaurantDTO;
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

    public static final String RESTAURANT_RESTAURANT_ID = "/api/restaurant/{restaurantUid}";
    public static final String RESTAURANT = "/api/restaurant";

    @Autowired
    public RestaurantClient(final ApplicationProperties applicationProperties, RestTemplate restTemplate, RestaurantMapper restaurantMapper)
    {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
        this.restaurantMapper = restaurantMapper;
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


}
