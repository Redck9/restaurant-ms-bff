package com.redck.restaurantmsbff.client;

import com.redck.restaurantmsbff.config.ApplicationProperties;
import com.redck.restaurantmsbff.domain.Table;
import com.redck.restaurantmsbff.logging.Logger;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.service.mapper.TableMapper;
import com.redck.restaurantmsbff.service.model.TableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class TableClient
{
    private static final Logger logger = new Logger(RestaurantClient.class);

    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;
    private final TableMapper tableMapper;

    public static final String TABLE_TABLE_ID = "/api/table/{tableUid}";
    public static final String TABLE = "/api/table";
    public static final String TABLE_RESTAURANT_ID = "/api/restaurant/{restaurantUid}/tables";

    @Autowired
    public TableClient(final ApplicationProperties applicationProperties, RestTemplate restTemplate, TableMapper tableMapper)
    {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
        this.tableMapper = tableMapper;
    }

    /**
     * Client to get Table by uid.
     * @param tableUid table uid.
     * @return table.
     */
    public Table getTable(final String tableUid)
    {
        final String url = applicationProperties.getMsManager().getUrl().concat(TABLE_TABLE_ID);
        final String correlationId = UUID.randomUUID().toString();
        TableDTO tableDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetTableUid = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("tableUid", tableUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                "Get Table by uid: " + tableUid);

        try
        {
            final ResponseEntity<TableDTO> responseTable = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetTableUid,
                    new ParameterizedTypeReference<TableDTO>() {
                    }, params);

            if(HttpStatus.OK.equals(responseTable.getStatusCode()))
            {
                tableDTO = responseTable.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                    "Table uid not found");
        }

        return tableMapper.mapTableDTOToTable(tableDTO);
    }

    /**
     * Client to create table.
     * @param table table.
     * @return table.
     */
    public Table createTable(final Table table)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(TABLE);
        final String correlationId = UUID.randomUUID().toString();
        TableDTO tableDTO = null;

        final TableDTO requestTableDTO = tableMapper.mapTableToTableDTO(table);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<TableDTO> requestCreateTableClient = new HttpEntity<>(requestTableDTO ,headers);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.PERSISTED),
                "Create Table: " + table.toString());

        try
        {
            final ResponseEntity<TableDTO> responseTableCreated = restTemplate.exchange(url, HttpMethod.POST,
                    requestCreateTableClient, TableDTO.class);
            if(HttpStatus.OK.equals(responseTableCreated.getStatusCode()))
            {
                tableDTO = responseTableCreated.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.PERSISTED)
                    , "Table was not created!!");
        }

        return tableMapper.mapTableDTOToTable(tableDTO);
    }

    /**
     * Client to edit table.
     * @param tableUid table uid.
     * @param table table.
     * @return table.
     */
    public Table editTable(final String tableUid, final Table table)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(TABLE_TABLE_ID);
        final String correlationId = UUID.randomUUID().toString();
        TableDTO tableDTO = null;

        final TableDTO requestTableDTO = tableMapper.mapTableToTableDTO(table);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<TableDTO> requestEditTableClient = new HttpEntity<>(requestTableDTO ,headers);
        final Map<String, String> params = new HashMap<>();
        params.put("tableUid", tableUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.EDITED),
                "Edit Table by uid " + tableUid);

        try
        {
            final ResponseEntity<TableDTO> responseTableEdited = restTemplate.exchange(url, HttpMethod.PUT,
                    requestEditTableClient, TableDTO.class, params);
            if(HttpStatus.OK.equals(responseTableEdited.getStatusCode()))
            {
                tableDTO = responseTableEdited.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.EDITED)
                    , "Table was not edited!!");
        }

        return tableMapper.mapTableDTOToTable(tableDTO);
    }

    /**
     * Client to delete table.
     * @param tableUid table id.
     * @return table deleted.
     */
    public Table deleteTable(final String tableUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(TABLE_TABLE_ID);
        final String correlationId = UUID.randomUUID().toString();
        TableDTO tableDTO = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetTableUid = new HttpEntity<>(headers);
        final Map<String, String> params = new HashMap<>();
        params.put("tableUid", tableUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.DELETED),
                "Delete Table by uid: " + tableUid);

        try
        {
            final ResponseEntity<TableDTO> responseTable = restTemplate.exchange(url, HttpMethod.DELETE,
                    requestGetTableUid,
                    new ParameterizedTypeReference<TableDTO>() {
                    }, params);
            if(HttpStatus.OK.equals(responseTable.getStatusCode()))
            {
                tableDTO = responseTable.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.DELETED),
                    "Table uid not found");
        }

        return tableMapper.mapTableDTOToTable(tableDTO);
    }

    /**
     * Client to get all tables.
     * @return tables list.
     */
    public List<Table> getAllTables()
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(TABLE);
        final String correlationId = UUID.randomUUID().toString();
        List<TableDTO> tableDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<Void> requestGetTableUid = new HttpEntity<>(headers);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                "Get All Tables list");

        try
        {
            final ResponseEntity<List<TableDTO>> responseTable = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetTableUid,
                    new ParameterizedTypeReference<List<TableDTO>>() {
                    });
            if(HttpStatus.OK.equals(responseTable.getStatusCode()))
            {
                tableDTOList = responseTable.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                    "Table list not found");
        }

        return tableMapper.mapTableDTOListToTableList(tableDTOList);
    }

    /**
     * Client to get all tables from a restaurant.
     * @param restaurantUid restaurant uid.
     * @return tables list.
     */
    public List<Table> getAllTablesFromRestaurant(final String restaurantUid)
    {
        final String url =  applicationProperties.getMsManager().getUrl().concat(TABLE_RESTAURANT_ID);
        final String correlationId = UUID.randomUUID().toString();
        List<TableDTO> tableDTOList = null;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Correlation-Id", correlationId);

        final HttpEntity<MultiValueMap<String, String>> requestGetRestaurantUid = new HttpEntity<>(headers);

        final Map<String, String> params = new HashMap<>();
        params.put("restaurantUid", restaurantUid);

        logger.info(correlationId, Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                "Get All Tables list from a Restaurant");

        try
        {
            final ResponseEntity<List<TableDTO>> responseTable = restTemplate.exchange(url, HttpMethod.GET,
                    requestGetRestaurantUid,
                    new ParameterizedTypeReference<List<TableDTO>>() {
                    }, params);
            if(HttpStatus.OK.equals(responseTable.getStatusCode()))
            {
                tableDTOList = responseTable.getBody();
            }
        }
        catch (final RestClientException restClientException)
        {
            logger.info(Arrays.asList(LogTag.CLIENT, LogTag.TABLES, LogTag.RETRIEVED),
                    "Table list not found from the Restaurant");
        }

        return tableMapper.mapTableDTOListToTableList(tableDTOList);
    }


}
