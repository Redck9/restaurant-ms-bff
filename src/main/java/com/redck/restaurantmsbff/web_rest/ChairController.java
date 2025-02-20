package com.redck.restaurantmsbff.web_rest;

import com.redck.restaurantmsbff.domain.Chair;
import com.redck.restaurantmsbff.service.ChairService;
import com.redck.restaurantmsbff.service.mapper.ChairMapper;
import com.redck.restaurantmsbff.service.model.ChairDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChairController implements ApiController
{
    public static final String CHAIR_CHAIR_ID = "/chair/{chairUid}";
    public static final String CHAIR = "/chair";
    public static final String CHAIR_TABLE_ID = "/table/{tableUid}/chairs";

    private final ChairService chairService;
    private final ChairMapper chairMapper;

    /**
     * Constructor for Chair Controller
     *
     * @param chairService     Chair Service
     * @param chairMapper      Chair Mapper
     */
    public ChairController(ChairService chairService, ChairMapper chairMapper)
    {
        this.chairService = chairService;
        this.chairMapper = chairMapper;
    }

    /**
     * Controller to get a chairDTO by uid
     * @param chairUid chairDTO uid to get
     * @return chairDTO with the provided uid
     */
    @GetMapping(value = CHAIR_CHAIR_ID,
            produces = {"application/json"})
    ResponseEntity<ChairDTO> getChair(@PathVariable final String chairUid)
    {

        return ResponseEntity.ok(chairMapper.mapChairToChairDTO(
                chairService.getChair(chairUid)));
    }

    /**
     * Controller to create a chairDTO
     * @param chairDTO chairDTO to create
     * @return chairDTO created
     */
    @PostMapping(value = CHAIR,
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<ChairDTO> createChair(@RequestBody final ChairDTO chairDTO)
    {

        return ResponseEntity.ok(chairMapper.mapChairToChairDTO(
                chairService.createChair(chairMapper.mapChairDTOToChair(chairDTO))));
    }

    /**
     * Controller to edit a chairDTO by uid
     * @param chairUid chairDTO uid to be edited
     * @param chairToEdit chairDTO update
     * @return chairDTO edited
     */
    @PutMapping(value = CHAIR_CHAIR_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<ChairDTO> editChair(@PathVariable final String chairUid, @RequestBody final ChairDTO chairToEdit)
    {

        return ResponseEntity.ok(chairMapper.mapChairToChairDTO(
                chairService.editChair(chairUid, chairMapper.mapChairDTOToChair(chairToEdit))));
    }

    /**
     * Controller to delete chairDTO by uid
     * @param chairUid chairDTO uid to be deleted
     * @return ChairDTO deleted
     */
    @DeleteMapping(value = CHAIR_CHAIR_ID,
            produces = {"application/json"})
    ResponseEntity<ChairDTO> deleteChair(@PathVariable final String chairUid)
    {

        return ResponseEntity.ok(chairMapper.mapChairToChairDTO(
                chairService.deleteChair(chairUid)));
    }

    /**
     * Controller to get a list with all chairDTO
     * @return chairDTO list
     */
    @GetMapping(value = CHAIR,
            produces = {"application/json"})
    ResponseEntity<List<ChairDTO>> getAllChairs()
    {

        return ResponseEntity.ok(chairMapper.mapChairListToChairDTOList(
                chairService.getAllChair()));
    }

    /**
     * Controller to get a list with all chairDTO from tableDTO
     * @param tableUid tableUid
     * @return chairDTO list
     */
    @GetMapping(value = CHAIR_TABLE_ID,
            produces = {"application/json"})
    ResponseEntity<List<ChairDTO>> getAllChairsFromTable(@PathVariable final String tableUid)
    {

        return ResponseEntity.ok(chairMapper.mapChairListToChairDTOList(
                chairService.getAllChairFromTable(tableUid)));
    }
}
