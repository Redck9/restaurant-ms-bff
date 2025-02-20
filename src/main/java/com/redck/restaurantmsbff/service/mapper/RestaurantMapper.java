package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.Restaurant;
import com.redck.restaurantmsbff.service.model.RestaurantDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper
{

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);

    List<Restaurant> mapRestaurantDTOListToRestaurantList(List<RestaurantDTO> restaurantDTO);

    List<RestaurantDTO> mapRestaurantListToRestaurantDTOList(List<Restaurant> restaurant);

}
