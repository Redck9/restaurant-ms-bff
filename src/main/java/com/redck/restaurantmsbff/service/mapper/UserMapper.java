package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.User;
import com.redck.restaurantmsbff.service.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDTOToUser(UserDTO userDTO);

    UserDTO mapUserToUserDTO(User user);

}
