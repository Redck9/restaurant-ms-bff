package com.redck.restaurantmsbff.domain;

public class Table
{
    private int tableId;

    private String restaurantUid;

    private String tableUid;

    private int chairsCapacity;

    private double xPosition;

    private double yPosition;

    private double angle;

    private double width;

    private double height;


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

    /**
     * Get Table xPosition.
     * @return Table xPosition.
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * Set Table xPosition
     * @param xPosition Table xPosition.
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Builder Table for xPosition.
     * @param xPosition xPosition to build.
     * @return Table with xPosition.
     */
    public Table xPosition(final double xPosition)
    {
        this.xPosition = xPosition;
        return this;
    }

    /**
     * Get Table yPosition.
     * @return Table yPosition.
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * Set Table yPosition
     * @param yPosition Table yPosition.
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Builder Table for yPosition.
     * @param yPosition yPosition to build.
     * @return Table with yPosition.
     */
    public Table yPosition(final double yPosition)
    {
        this.yPosition = yPosition;
        return this;
    }

    /**
     * Get Table angle.
     * @return Table angle.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Set Table angle
     * @param angle Table angle.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Builder Table for angle.
     * @param angle angle to build.
     * @return Table with angle.
     */
    public Table angle(final double angle)
    {
        this.angle = angle;
        return this;
    }

    /**
     * Get Table width.
     * @return Table width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set Table width
     * @param width Table width.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Builder Table for width.
     * @param width width to build.
     * @return Table with width.
     */
    public Table width(final double width)
    {
        this.width = width;
        return this;
    }

    /**
     * Get Table height.
     * @return Table height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set Table height
     * @param height Table height.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Builder Table for height.
     * @param height height to build.
     * @return Table with height.
     */
    public Table height(final double height)
    {
        this.height = height;
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
                ", xPosition = " + xPosition +
                ", yPosition = " + yPosition +
                ", angle = " + angle +
                ", width = " + width +
                ", height = " + height +
                " }";
    }
}
