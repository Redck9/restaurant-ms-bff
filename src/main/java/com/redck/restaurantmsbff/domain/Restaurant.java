package com.redck.restaurantmsbff.domain;

import java.util.List;

public class Restaurant {

    private long id;

    private String uid;

    private String name;

    private String type;

    private String location;

    private int tablesCapacity;

    private String description;

    private String userUid;

    private List<Schedule> schedule;

    private List<MenuItem> menuItem;

    private String url;

    //private byte[] schedule;

    /**
     * Get Restaurant id.
     * @return restaurant id.
     */
    public long getId()
    {
        return id;
    }

    /**
     * Set Restaurant id.
     * @param id restaurant id.
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Builder Restaurant for id.
     * @param id id to build.
     * @return Restaurant with id.
     */
    public Restaurant id(final long id)
    {
        this.id = id;
        return this;
    }

    /**
     * Get Restaurant uid.
     * @return restaurant uid.
     */
    public String getUid()
    {
        return uid;
    }

    /**
     * Set Restaurant uid.
     * @param uid Restaurant uid.
     */
    public void setUid(String uid)
    {
        this.uid = uid;
    }

    /**
     * Builder Restaurant for uid.
     * @param uid uid to build.
     * @return Restaurant with uid.
     */
    public Restaurant uid(final String uid)
    {
        this.uid = uid;
        return this;
    }

    /**
     * Get Restaurant name.
     * @return Restaurant name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set Restaurant name.
     * @param name Restaurant name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Builder Restaurant for name.
     * @param name name to build.
     * @return Restaurant with name.
     */
    public Restaurant name(final String name)
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

    public Restaurant type(final String type)
    {
        this.type = type;
        return this;
    }

    /**
     * Get Restaurant location.
     * @return Restaurant location.
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * Set Restaurant location.
     * @param location Restaurant location.
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     * Builder Restaurant for location
     * @param location location to build.
     * @return Restaurant with location.
     */
    public Restaurant location(final String location)
    {
        this.location = location;
        return this;
    }

    /*
        public byte[] getSchedule()
        {
            return schedule;
        }

        public void setSchedule(byte[] schedule)
        {
            this.schedule = schedule;
        }

        public Restaurant schedule(final)

    */

    /**
     * Get Restaurant tablesCapacity.
     * @return Restaurant tablesCapacity.
     */
    public int getTablesCapacity() {
        return tablesCapacity;
    }

    /**
     * Set Restaurant tablesCapacity.
     * @param tablesCapacity Restaurant tablesCapacity.
     */
    public void setTablesCapacity(int tablesCapacity) {
        this.tablesCapacity = tablesCapacity;
    }

    /**
     * Builder Restaurant for tablesCapacity.
     * @param tablesCapacity tablesCapacity to build.
     * @return Restaurant with tablesCapacity.
     */
    public Restaurant tablesCapacity(final int tablesCapacity)
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

    public Restaurant description(final String description)
    {
        this.description = description;
        return this;
    }

    /**
     * Get Restaurant userUid.
     * @return restaurant userUid.
     */
    public String getUserUid()
    {
        return userUid;
    }

    /**
     * Set Restaurant userUid.
     * @param userUid restaurant userUid.
     */
    public void setUserUid(String userUid)
    {
        this.userUid = userUid;
    }

    /**
     * Builder Restaurant for userUid.
     * @param userUid userUid to build.
     * @return Restaurant with userUid.
     */
    public Restaurant userUid(final String userUid)
    {
        this.userUid = userUid;
        return this;
    }

    public List<Schedule> getSchedule()
    {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule)
    {
        this.schedule = schedule;
    }

    public Restaurant schedule(final List<Schedule> schedule)
    {
        this.schedule = schedule;
        return this;
    }

    public List<MenuItem> getMenuItem()
    {
        return menuItem;
    }

    public void setMenuItem(List<MenuItem> menuItem)
    {
        this.menuItem = menuItem;
    }

    public Restaurant menuItem(final List<MenuItem> menuItem)
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

    public Restaurant url(final String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public String toString()
    {
        return "Restaurant {" +
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
