package com.redck.restaurantmsbff.domain;

public class Table
{
    private int tableId;

    private String restaurantUid;

    private String tableUid;

    private int chairsCapacity;


    /**
     * Get Table id.
     * @return Table id.
     */
    public int getTableId()
    {
        return tableId;
    }

    /**
     * Set Table id
     * @param tableId table id.
     */
    public void setTableId(int tableId)
    {
        this.tableId = tableId;
    }

    /**
     * Builder Table for id.
     * @param tableId tableId to build
     * @return Table with id.
     */
    public Table tableId(final int tableId)
    {
        this.tableId = tableId;
        return this;
    }

    /**
     * Get Restaurant restaurantUid.
     * @return Restaurant restaurantUid
     */
    public String getRestaurantUid()
    {
        return restaurantUid;
    }

    /**
     * Set Table restaurantUid.
     * @param restaurantUid restaurant uid.
     */
    public void setRestaurantUid(Restaurant restaurantUid)
    {
        if(restaurantUid != null)
        {
            this.restaurantUid = restaurantUid.getUid();
        }
    }

    /**
     * Builder Table for restaurantUid.
     * @param restaurantUid restaurantUid to build.
     * @return Table with restaurant uid.
     */
    public Table restaurantUid(final String restaurantUid)
    {
        this.restaurantUid = restaurantUid;
        return this;
    }

    /**
     * Get Table uid.
     * @return Table uid.
     */
    public String getTableUid()
    {
        return tableUid;
    }

    /**
     * Set Table uid.
     * @param tableUid Table uid.
     */
    public void setTableUid(String tableUid)
    {
        this.tableUid = tableUid;
    }

    /**
     * Builder Table uid.
     * @param tableUid tableUid to build.
     * @return Table with uid.
     */
    public Table tableUid(final String tableUid)
    {
        this.tableUid = tableUid;
        return this;
    }

    /**
     * Get Table chairsCapacity.
     * @return Table chairsCapacity.
     */
    public int getChairsCapacity()
    {
        return chairsCapacity;
    }

    /**
     * Set Table chairsCapacity.
     * @param chairsCapacity Table chairsCapacity.
     */
    public void setChairsCapacity(int chairsCapacity)
    {
        this.chairsCapacity = chairsCapacity;
    }

    /**
     * Builder Table chairsCapacity.
     * @param chairsCapacity chairsCapacity to build.
     * @return Table with chairsCapacity.
     */
    public Table chairsCapacity(final int chairsCapacity)
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
