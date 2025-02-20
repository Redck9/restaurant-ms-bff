package com.redck.restaurantmsbff.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TableDTO implements Serializable
{
    @JsonProperty("tableId")
    private int tableId;

    @JsonProperty("restaurantUid")
    private String restaurantUid;

    @JsonProperty("tableUid")
    private String tableUid;

    @JsonProperty("chairsCapacity")
    private int chairsCapacity;

    /**
     * Get TableDTO id.
     * @return TableDTO id.
     */
    public int getTableId()
    {
        return tableId;
    }

    /**
     * Set TableDTO id
     * @param tableId tableDTO id.
     */
    public void setTableId(int tableId)
    {
        this.tableId = tableId;
    }

    /**
     * Builder TableDTO for id.
     * @param tableId tableId to build
     * @return TableDTO with id.
     */
    public TableDTO tableId(final int tableId)
    {
        this.tableId = tableId;
        return this;
    }

    /**
     * Get RestaurantDTO restaurantUid.
     * @return RestaurantDTO restaurantUid
     */
    public String getRestaurantUid()
    {
        return restaurantUid;
    }

    /**
     * Set TableDTO restaurantUid.
     * @param restaurantUid restaurantDTO uid.
     */
    public void setRestaurantUid(String restaurantUid)
    {
        this.restaurantUid = restaurantUid;
    }

    /**
     * Builder TableDTO for restaurantUid.
     * @param restaurantUid restaurantUid to build.
     * @return TableDTO with restaurant uid.
     */
    public TableDTO restaurantUid(final String restaurantUid)
    {
        this.restaurantUid = restaurantUid;
        return this;
    }

    /**
     * Get TableDTO uid.
     * @return TableDTO uid.
     */
    public String getTableUid() {
        return tableUid;
    }

    /**
     * Set TableDTO uid.
     * @param tableUid TableDTO uid.
     */
    public void setTableUid(String tableUid) {
        this.tableUid = tableUid;
    }

    /**
     * Builder TableDTO uid.
     * @param tableUid tableUid to build.
     * @return TableDTO with uid.
     */
    public TableDTO tableUid(final String tableUid)
    {
        this.tableUid = tableUid;
        return this;
    }

    /**
     * Get TableDTO chairsCapacity.
     * @return TableDTO chairsCapacity.
     */
    public int getChairsCapacity() {
        return chairsCapacity;
    }

    /**
     * Set TableDTO chairsCapacity.
     * @param chairsCapacity TableDTO chairsCapacity.
     */
    public void setChairsCapacity(int chairsCapacity) {
        this.chairsCapacity = chairsCapacity;
    }

    /**
     * Builder TableDTO chairsCapacity.
     * @param chairsCapacity chairsCapacity to build.
     * @return TableDTO with chairsCapacity.
     */
    public TableDTO chairsCapacity(final int chairsCapacity)
    {
        this.chairsCapacity = chairsCapacity;
        return this;
    }

    @Override
    public String toString()
    {
        return "Table {" +
                "tableId = " + tableId +
                ", restaurantUid = " + restaurantUid +
                ", tableUid = " + tableUid +
                ", chairsCapacity = " + chairsCapacity +
                " }";
    }

}
