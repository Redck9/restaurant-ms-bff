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

    @JsonProperty("xPosition")
    private double xPosition;

    @JsonProperty("yPosition")
    private double yPosition;

    @JsonProperty("angle")
    private double angle;

    @JsonProperty("width")
    private double width;

    @JsonProperty("height")
    private double height;

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

    /**
     * Get TableDTO xPosition.
     * @return TableDTO xPosition.
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * Set TableDTO xPosition
     * @param xPosition TableDTO xPosition.
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Builder TableDTO for xPosition.
     * @param xPosition xPosition to build.
     * @return TableDTO with xPosition.
     */
    public TableDTO xPosition(final double xPosition)
    {
        this.xPosition = xPosition;
        return this;
    }

    /**
     * Get TableDTO yPosition.
     * @return TableDTO yPosition.
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * Set TableDTO yPosition
     * @param yPosition TableDTO yPosition.
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Builder TableDTO for yPosition.
     * @param yPosition yPosition to build.
     * @return TableDTO with yPosition.
     */
    public TableDTO yPosition(final double yPosition)
    {
        this.yPosition = yPosition;
        return this;
    }

    /**
     * Get TableDTO angle.
     * @return TableDTO angle.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Set TableDTO angle
     * @param angle TableDTO angle.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Builder TableDTO for angle.
     * @param angle angle to build.
     * @return TableDTO with angle.
     */
    public TableDTO angle(final double angle)
    {
        this.angle = angle;
        return this;
    }

    /**
     * Get TableDTO width.
     * @return TableDTO width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set TableDTO width
     * @param width TableDTO width.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Builder TableDTO for width.
     * @param width width to build.
     * @return TableDTO with width.
     */
    public TableDTO width(final double width)
    {
        this.width = width;
        return this;
    }

    /**
     * Get TableDTO height.
     * @return TableDTO height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set TableDTO height
     * @param height TableDTO height.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Builder TableDTO for height.
     * @param height height to build.
     * @return TableDTO with height.
     */
    public TableDTO height(final double height)
    {
        this.height = height;
        return this;
    }


    @Override
    public String toString()
    {
        return "TableDTO {" +
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
