package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.Table;
import com.redck.restaurantmsbff.service.model.TableDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper
{
    Table mapTableDTOToTable(TableDTO tableDTO);

    TableDTO mapTableToTableDTO(Table table);

    List<Table> mapTableDTOListToTableList(List<TableDTO> tableDTO);

    List<TableDTO> mapTableListToTableDTOList(List<Table> table);
}
