package com.redck.restaurantmsbff.client;

import com.redck.restaurantmsbff.config.ApplicationProperties;
import com.redck.restaurantmsbff.domain.Chair;
import com.redck.restaurantmsbff.logging.Logger;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.service.mapper.ChairMapper;
import com.redck.restaurantmsbff.service.model.ChairDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ChairClient
{
    private static final Logger logger = new Logger(RestaurantClient.class);

    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;
    private final ChairMapper chairMapper;

    public static final String CHAIR_CHAIR_ID = "/api/chair/{chairUid}";
    public static final String CHAIR = "/api/chair";
    public static final String CHAIR_TABLE_ID = "/api/table/{tableUid}/chairs";

    @Autowired
    public ChairClient(final ApplicationProperties applicationProperties, RestTemplate restTemplate, ChairMapper chairMapper)
    {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
        this.chairMapper = chairMapper;
    }

    /**
     * Client to get Chair by uid.
     * @param chairUid table uid.
     * @return table.
     */
    public Chair getChair(final String chairUid)
    {
        final String url = applicationProperties.getMsManager().getUrl().concat(CHAIR_CHAIR_ID);
        final String correlationId = UUID.randomUUID().toString();
        ChairDTO chairDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetChairUid = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("chairUid", chairUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                "Get Chair by uid: " + chairUid);

        try
        {
            final ResponseEntity<ChairDTO> responseChair = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetChairUid,
                    new ParameterizedTypeReference<ChairDTO>() {
                    }, params);

            if(HttpStatus.OK.equals(responseChair.getStatusCode()))
            {
                chairDTO = responseChair.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                    "Chair uid not found");
        }

        return chairMapper.mapChairDTOToChair(chairDTO);
    }

    /**
     * Client to create table.
     * @param table table.
     * @return table.
     */
    public Chair createChair(final Chair table)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(CHAIR);
        final String correlationId = UUID.randomUUID().toString();
        ChairDTO chairDTO = null;

        final ChairDTO requestChairDTO = chairMapper.mapChairToChairDTO(table);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<ChairDTO> requestCreateChairClient = new HttpEntity<>(requestChairDTO ,headers);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.PERSISTED),
                "Create Chair: " + table.toString());

        try
        {
            final ResponseEntity<ChairDTO> responseChairCreated = restTemplate.exchange(url, HttpMethod.POST,
                    requestCreateChairClient, ChairDTO.class);
            if(HttpStatus.OK.equals(responseChairCreated.getStatusCode()))
            {
                chairDTO = responseChairCreated.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.PERSISTED)
                    , "Chair was not created!!");
        }

        return chairMapper.mapChairDTOToChair(chairDTO);
    }

    /**
     * Client to edit table.
     * @param chairUid table uid.
     * @param table table.
     * @return table.
     */
    public Chair editChair(final String chairUid, final Chair table)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(CHAIR_CHAIR_ID);
        final String correlationId = UUID.randomUUID().toString();
        ChairDTO chairDTO = null;

        final ChairDTO requestChairDTO = chairMapper.mapChairToChairDTO(table);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<ChairDTO> requestEditChairClient = new HttpEntity<>(requestChairDTO ,headers);
        final Map<String, String> params = new HashMap<>();
        params.put("chairUid", chairUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.EDITED),
                "Edit Chair by uid " + chairUid);

        try
        {
            final ResponseEntity<ChairDTO> responseChairEdited = restTemplate.exchange(url, HttpMethod.PUT,
                    requestEditChairClient, ChairDTO.class, params);
            if(HttpStatus.OK.equals(responseChairEdited.getStatusCode()))
            {
                chairDTO = responseChairEdited.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.EDITED)
                    , "Chair was not edited!!");
        }

        return chairMapper.mapChairDTOToChair(chairDTO);
    }

    /**
     * Client to delete table.
     * @param chairUid table id.
     * @return table deleted.
     */
    public Chair deleteChair(final String chairUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(CHAIR_CHAIR_ID);
        final String correlationId = UUID.randomUUID().toString();
        ChairDTO chairDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetChairUid = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("chairUid", chairUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.DELETED),
                "Delete Chair by uid: " + chairUid);

        try
        {
            final ResponseEntity<ChairDTO> responseChair = restTemplate.exchange(url, HttpMethod.DELETE,
                    requestGetChairUid,
                    new ParameterizedTypeReference<ChairDTO>() {
                    }, params);
            if(HttpStatus.OK.equals(responseChair.getStatusCode()))
            {
                chairDTO = responseChair.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.DELETED),
                    "Chair uid not found");
        }

        return chairMapper.mapChairDTOToChair(chairDTO);
    }

    /**
     * Client to get all tables.
     * @return tables list.
     */
    public List<Chair> getAllChair()
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(CHAIR);
        final String correlationId = UUID.randomUUID().toString();
        List<ChairDTO> chairDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestGetChairUid = new HttpEntity<>(headers);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                "Get All Chairs list");

        try
        {
            final ResponseEntity<List<ChairDTO>> responseChair = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetChairUid,
                    new ParameterizedTypeReference<List<ChairDTO>>() {
                    });
            if(HttpStatus.OK.equals(responseChair.getStatusCode()))
            {
                chairDTOList = responseChair.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                    "Chair list not found");
        }

        return chairMapper.mapChairDTOListToChairList(chairDTOList);
    }

    /**
     * Client to get all chairs from a table.
     * @param tableUid table uid.
     * @return chairs list.
     */
    public List<Chair> getAllChairFromTable(final String tableUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(CHAIR_TABLE_ID);
        final String correlationId = UUID.randomUUID().toString();
        List<ChairDTO> chairDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetTableUid = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("tableUid", tableUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                "Get All Chairs list from a Restaurant");

        try
        {
            final ResponseEntity<List<ChairDTO>> responseChair = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetTableUid,
                    new ParameterizedTypeReference<List<ChairDTO>>() {
                    }, params);
            if(HttpStatus.OK.equals(responseChair.getStatusCode()))
            {
                chairDTOList = responseChair.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                    "Chair list not found from the Restaurant");
        }

        return chairMapper.mapChairDTOListToChairList(chairDTOList);
    }


}
