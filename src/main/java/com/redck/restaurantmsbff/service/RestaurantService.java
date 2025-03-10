package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.client.RestaurantClient;
import com.redck.restaurantmsbff.domain.MenuItem;
import com.redck.restaurantmsbff.domain.Restaurant;
import com.redck.restaurantmsbff.domain.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService
{
    private final RestaurantClient restaurantClient;

    @Autowired
    public RestaurantService(final RestaurantClient restaurantClient)
    {
        this.restaurantClient = restaurantClient;
    }

    /**
     * Call get restaurant client.
     * @param restaurantUid restaurant uid.
     * @return restaurant.
     */
    public Restaurant getRestaurant(final String restaurantUid)
    {
        return restaurantClient.getRestaurant(restaurantUid);
    }

    /**
     * Call create restaurant client.
     * @param restaurant restaurant to create.
     * @return restaurant.
     */
    public Restaurant createRestaurant(final Restaurant restaurant)
    {
        return restaurantClient.createRestaurant(restaurant);
    }

    /**
     * Call edit restaurant client.
     * @param restaurantUid restaurant uid to edit.
     * @param restaurant restaurant to be edited
     * @return restaurant edited.
     */
    public Restaurant editRestaurant(final String restaurantUid, final Restaurant restaurant)
    {
        return restaurantClient.editRestaurant(restaurantUid, restaurant);
    }

    /**
     * Call remove restaurant.
     * @param restaurantUid restaurant uid.
     * @return Restaurant Deleted.
     */
    public Restaurant deleteRestaurant(final String restaurantUid)
    {
        return restaurantClient.deleteRestaurant(restaurantUid);
    }

    /**
     * Call Get all restaurants.
     * @return Restaurant List.
     */
    public List<Restaurant> getAllRestaurants()
    {
        return restaurantClient.getAllRestaurants();
    }

    public Restaurant addMenuItem(final String restaurantUid, final MenuItem menuItem)
    {
        return  restaurantClient.addMenuItem(restaurantUid, menuItem);
    }

    public Restaurant addSchedule(final String restaurantUid, final Schedule schedule)
    {
        return restaurantClient.addSchedule(restaurantUid, schedule);
    }

    public Restaurant deleteMenuItem(final String restaurantUid, final String menuItemName)
    {
        return restaurantClient.deleteMenuItem(restaurantUid, menuItemName);
    }

    public Restaurant deleteSchedule(final String restaurantUid, final String day)
    {
        return restaurantClient.deleteSchedule(restaurantUid, day);
    }

    public List<MenuItem> getMenu(final String restaurantUid)
    {
        return restaurantClient.getMenu(restaurantUid);
    }

    public MenuItem getMenuItem(final String restaurantUid, final String menuItemName)
    {
        return restaurantClient.getMenuItem(restaurantUid, menuItemName);
    }

    public List<Schedule> getSchedule(final String restaurantUid)
    {
        return restaurantClient.getSchedule(restaurantUid);
    }

    public Schedule getScheduleByDay(final String restaurantUid, final String day)
    {
        return restaurantClient.getScheduleByDay(restaurantUid, day);
    }


}
