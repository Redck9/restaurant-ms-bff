package com.redck.restaurantmsbff.service.mapper;

import com.redck.restaurantmsbff.domain.MenuItem;
import com.redck.restaurantmsbff.service.model.MenuItemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper
{
    MenuItem mapMenuItemDTOToMenuItem(MenuItemDTO menuItemDTO);

    MenuItemDTO mapMenuItemToMenuItemDTO(MenuItem menuItem);

    List<MenuItem> mapMenuItemDTOListToMenuItemList(List<MenuItemDTO> menuItemDTO);

    List<MenuItemDTO> mapMenuItemListToMenuItemDTOList(List<MenuItem> menuItem);
}
