package com.redck.restaurantmsbff.web_rest;

import com.redck.restaurantmsbff.domain.Table;
import com.redck.restaurantmsbff.service.TableService;
import com.redck.restaurantmsbff.service.mapper.TableMapper;
import com.redck.restaurantmsbff.service.model.TableDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableController implements ApiController
{

    public static final String TABLE_TABLE_ID = "/table/{tableUid}";
    public static final String TABLE = "/table";
    public static final String TABLE_RESTAURANT_ID = "/restaurant/{restaurantUid}/tables";

    private final TableService tableService;
    private final TableMapper tableMapper;


    /**
     * Constructor for Table Controller
     *
     * @param tableService     Table Service
     * @param tableMapper      Table Mapper
     */
    public TableController(TableService tableService, TableMapper tableMapper) {
        this.tableService = tableService;
        this.tableMapper = tableMapper;
    }

    /**
     * Controller to get a tableDTO by uid
     * @param tableUid tableDTO uid to get
     * @return tableDTO with the provided uid
     */
    @GetMapping(value = TABLE_TABLE_ID,
            produces = {"application/json"})
    ResponseEntity<TableDTO> getTable(@PathVariable final String tableUid)
    {

        return ResponseEntity.ok(tableMapper.mapTableToTableDTO(
                tableService.getTable(tableUid)));
    }

    /**
     * Controller to create a tableDTO
     * @param tableDTO tableDTO to create
     * @return tableDTO created
     */
    @PostMapping(value = TABLE,
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<TableDTO> createTable(@RequestBody final TableDTO tableDTO)
    {

        return ResponseEntity.ok(tableMapper.mapTableToTableDTO(
                tableService.createTable(tableMapper.mapTableDTOToTable(tableDTO))));
    }

    /**
     * Controller to edit a tableDTO by uid
     * @param tableUid tableDTO uid to be edited
     * @param tableToEdit tableDTO update
     * @return tableDTO edited
     */
    @PutMapping(value = TABLE_TABLE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<TableDTO> editTable(@PathVariable final String tableUid, @RequestBody final TableDTO tableToEdit)
    {

        return ResponseEntity.ok(tableMapper.mapTableToTableDTO(
                tableService.editTable(tableUid, tableMapper.mapTableDTOToTable(tableToEdit))));
    }

    /**
     * Controller to delete tableDTO by uid
     * @param tableUid tableDTO uid to be deleted
     * @return TableDTO deleted
     */
    @DeleteMapping(value = TABLE_TABLE_ID,
            produces = {"application/json"})
    ResponseEntity<TableDTO> deleteTable(@PathVariable final String tableUid)
    {

        return ResponseEntity.ok(tableMapper.mapTableToTableDTO(
                tableService.deleteTable(tableUid)));
    }

    /**
     * Controller to get a list with all tableDTO
     * @return tableDTO list
     */
    @GetMapping(value = TABLE,
            produces = {"application/json"})
    ResponseEntity<List<TableDTO>> getAllTables()
    {

        return ResponseEntity.ok(tableMapper.mapTableListToTableDTOList(
                tableService.getAllTables()));
    }

    /**
     * Controller to get a list with all tableDTO from RestaurantDTO
     * @param restaurantUid restaurantUid
     * @return tableDTO list
     */
    @GetMapping(value = TABLE_RESTAURANT_ID,
            produces = {"application/json"})
    ResponseEntity<List<TableDTO>> getAllTablesFromRestaurant(@PathVariable final String restaurantUid)
    {

        return ResponseEntity.ok(tableMapper.mapTableListToTableDTOList(
                tableService.getAllTablesFromRestaurant(restaurantUid)));
    }
}
