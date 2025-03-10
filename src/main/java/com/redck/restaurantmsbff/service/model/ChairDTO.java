package com.redck.restaurantmsbff.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ChairDTO implements Serializable
{
    @JsonProperty("chairId")
    private long chairId;

    @JsonProperty("tableUid")
    private String tableUid;

    @JsonProperty("chairUid")
    private String chairUid;

    @JsonProperty("occupied")
    private Boolean occupied;

    @JsonProperty("xPosition")
    private double xPosition;

    @JsonProperty("yPosition")
    private double yPosition;

    @JsonProperty("angle")
    private double angle;

    /**
     * Get ChairDTO id.
     * @return ChairDTO id.
     */
    public long getChairId() {
        return chairId;
    }

    /**
     * Set ChairDTO id
     * @param chairId ChairDTO id.
     */
    public void setChairId(long chairId) {
        this.chairId = chairId;
    }

    /**
     * Builder ChairDTO for id.
     * @param chairId chairId to build.
     * @return ChairDTO with id.
     */
    public ChairDTO chairId(final long chairId)
    {
        this.chairId = chairId;
        return this;
    }

    /**
     * Get TableDTO tableUid.
     * @return TableDTO tableUid.
     */
    public String getTableUid()
    {
        return tableUid;
    }

    /**
     * Set Chair tableUid.
     * @param tableUid table uid.
     */
    public void setTableUid(String tableUid)
    {
        this.tableUid = tableUid;
    }

    /**
     * Builder ChairDTO for tableUid.
     * @param tableUid tableUid to build.
     * @return ChairDTO with table uid.
     */
    public ChairDTO tableUid(final String tableUid)
    {
        this.tableUid = tableUid;
        return this;
    }

    /**
     * Get ChairDTO uid.
     * @return ChairDTO uid.
     */
    public String getChairUid() {
        return chairUid;
    }

    /**
     * Set ChairDTO uid.
     * @param chairUid ChairDTO uid.
     */
    public void setChairUid(String chairUid) {
        this.chairUid = chairUid;
    }

    /**
     * Builder ChairDTO uid.
     * @param chairUid chairUid to build.
     * @return ChairDTO with uid.
     */
    public ChairDTO chairUid(final String chairUid)
    {
        this.chairUid = chairUid;
        return this;
    }

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public ChairDTO occupied(final Boolean occupied)
    {
        this.occupied = occupied;
        return this;
    }

    /**
     * Get ChairDTO xPosition.
     * @return ChairDTO xPosition.
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * Set ChairDTO xPosition
     * @param xPosition ChairDTO xPosition.
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Builder ChairDTO for xPosition.
     * @param xPosition xPosition to build.
     * @return ChairDTO with xPosition.
     */
    public ChairDTO xPosition(final double xPosition)
    {
        this.xPosition = xPosition;
        return this;
    }

    /**
     * Get ChairDTO yPosition.
     * @return ChairDTO yPosition.
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * Set ChairDTO yPosition
     * @param yPosition ChairDTO yPosition.
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Builder ChairDTO for yPosition.
     * @param yPosition yPosition to build.
     * @return ChairDTO with yPosition.
     */
    public ChairDTO yPosition(final double yPosition)
    {
        this.yPosition = yPosition;
        return this;
    }

    /**
     * Get ChairDTO angle.
     * @return ChairDTO angle.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Set ChairDTO angle
     * @param angle ChairDTO angle.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Builder ChairDTO for angle.
     * @param angle angle to build.
     * @return ChairDTO with angle.
     */
    public ChairDTO angle(final double angle)
    {
        this.angle = angle;
        return this;
    }

    @Override
    public String toString()
    {
        return "ChairDTO {" +
                "chairId = " + chairId +
                ", tableUid = " + tableUid +
                ", chairUid = " + chairUid +
                ", occupied = " + occupied +
                ", xPosition = " + xPosition +
                ", yPosition = " + yPosition +
                ", angle = " + angle +
                " }";
    }
}
