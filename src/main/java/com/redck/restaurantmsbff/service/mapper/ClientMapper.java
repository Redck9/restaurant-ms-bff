package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.service.model.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client mapClientDTOToClient(ClientDTO clientDTO);

    ClientDTO mapClientToClientDTO(Client client);

}
