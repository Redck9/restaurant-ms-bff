package com.redck.restaurantmsbff.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class RestaurantDTO implements Serializable
{
    @JsonProperty("id")
    private long id;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("location")
    private String location;

    @JsonProperty("tablesCapacity")
    private int tablesCapacity;

    @JsonProperty("description")
    private String description;

    @JsonProperty("userUid")
    private String userUid;

    @JsonProperty("schedule")
    private List<ScheduleDTO> schedule;

    @JsonProperty("menuItem")
    private List<MenuItemDTO> menuItem;

    @JsonProperty("url")
    private String url;

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public RestaurantDTO type(final String type)
    {
        this.type = type;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public RestaurantDTO description(final String description)
    {
        this.description = description;
        return this;
    }

    /**
     * Get RestaurantDTO userUid.
     * @return RestaurantDTO userUid.
     */
    public String getUserUid()
    {
        return userUid;
    }

    /**
     * Set RestaurantDTO userUid.
     * @param userUid RestaurantDTO userUid.
     */
    public void setUserUid(String userUid)
    {
        this.userUid = userUid;
    }

    /**
     * Builder RestaurantDTO for userUid.
     * @param userUid userUid to build.
     * @return RestaurantDTO with userUid.
     */
    public RestaurantDTO userUid(final String userUid)
    {
        this.userUid = userUid;
        return this;
    }

    public List<ScheduleDTO> getSchedule()
    {
        return schedule;
    }

    public void setSchedule(List<ScheduleDTO> schedule)
    {
        this.schedule = schedule;
    }

    public RestaurantDTO schedule(final List<ScheduleDTO> schedule)
    {
        this.schedule = schedule;
        return this;
    }

    public List<MenuItemDTO> getMenuItem()
    {
        return menuItem;
    }

    public void setMenuItem(List<MenuItemDTO> menuItem)
    {
        this.menuItem = menuItem;
    }

    public RestaurantDTO menuItem(final List<MenuItemDTO> menuItem)
    {
        this.menuItem = menuItem;
        return this;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public RestaurantDTO url(final String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public String toString()
    {
        return "RestaurantDTO{" +
                "id = " + id +
                ", uid = " + uid +
                ", name = " + name +
                ", type = " + type +
                ", location = " + location +
                ", tablesCapacity = " + tablesCapacity +
                ", description = " + description +
                ", schedule = " + schedule +
                ", menuItem = " + menuItem +
                ", userUid = " + userUid +
                ", url = " + url +
                " }";
    }

}