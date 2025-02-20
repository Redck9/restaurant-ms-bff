package com.redck.restaurantmsbff.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RestaurantDTO implements Serializable
{
    @JsonProperty("id")
    private long id;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("tablesCapacity")
    private int tablesCapacity;

    /**
     * Get RestaurantDTO id
     * @return restaurantDTO id
     */
    public long getId() {
        return id;
    }

    /**
     * Set RestaurantDTO id.
     * @param id restaurantDTO id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Builder RestaurantDTO for id.
     * @param id id to build.
     * @return RestaurantDTO with id.
     */
    public RestaurantDTO id(final long id)
    {
        this.id = id;
        return this;
    }

    /**
     * Get RestaurantDTO uid.
     * @return restaurantDTO uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set RestaurantDTO uid.
     * @param uid RestaurantDTO uid.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Builder RestaurantDTO for uid.
     * @param uid uid to build.
     * @return RestaurantDTO with uid.
     */
    public RestaurantDTO uid(final String uid)
    {
        this.uid = uid;
        return this;
    }

    /**
     * Get RestaurantDTO name.
     * @return RestaurantDTO name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set RestaurantDTO name.
     * @param name RestaurantDTO name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Builder RestaurantDTO for name.
     * @param name name to build.
     * @return RestaurantDTO with name.
     */
    public RestaurantDTO name(final String name)
    {
        this.name = name;
        return this;
    }

    /**
     * Get RestaurantDTO location.
     * @return RestaurantDTO location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set RestaurantDTO location.
     * @param location RestaurantDTO location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Builder RestaurantDTO for location
     * @param location location to build.
     * @return RestaurantDTO with location.
     */
    public RestaurantDTO location(final String location)
    {
        this.location = location;
        return this;
    }

    /**
     * Get RestaurantDTO tablesCapacity.
     * @return RestaurantDTO tablesCapacity.
     */
    public int getTablesCapacity() {
        return tablesCapacity;
    }

    /**
     * Set RestaurantDTO tablesCapacity.
     * @param tablesCapacity RestaurantDTO tablesCapacity.
     */
    public void setTablesCapacity(int tablesCapacity) {
        this.tablesCapacity = tablesCapacity;
    }

    /**
     * Builder RestaurantDTO for tablesCapacity.
     * @param tablesCapacity tablesCapacity to build.
     * @return RestaurantDTO with tablesCapacity.
     */
    public RestaurantDTO tablesCapacity(final int tablesCapacity)
    {
        this.tablesCapacity = tablesCapacity;
        return this;
    }

    /**
     * Get RestaurantDTO tableList.
     * @return RestaurantDTO tableList.
     */

    @Override
    public String toString()
    {
        return "RestaurantDTO{" +
                "id = " + id +
                ", uid = " + uid +
                ", name = " + name +
                ", location = " + location +
                ", tablesCapacity = " + tablesCapacity +
                " }";
    }

}