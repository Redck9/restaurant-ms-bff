package com.redck.restaurantmsbff.domain;

public class Restaurant
{

    private long id;

    private String uid;

    private String name;

    private String location;

    private int tablesCapacity;

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

    @Override
    public String toString()
    {
        return "Restaurant {" +
                "id = " + id +
                ", uid = " + uid +
                ", name = " + name +
                ", location = " + location +
                ", tablesCapacity = " + tablesCapacity +
                " }";
    }
}