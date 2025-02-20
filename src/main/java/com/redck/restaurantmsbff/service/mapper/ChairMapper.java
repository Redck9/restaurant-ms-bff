package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.Chair;
import com.redck.restaurantmsbff.service.model.ChairDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChairMapper
{
    Chair mapChairDTOToChair(ChairDTO chairDTO);

    ChairDTO mapChairToChairDTO(Chair chair);

    List<Chair> mapChairDTOListToChairList(List<ChairDTO> chairDTO);

    List<ChairDTO> mapChairListToChairDTOList(List<Chair> chair);
}
