package com.redck.restaurantmsbff.domain;

public class Chair
{
    private long chairId;

    private String tableUid;

    private String chairUid;

    private Boolean occupied;

    private double xPosition;

    private double yPosition;

    private double angle;

    /**
     * Get Chair id.
     * @return Chair id.
     */
    public long getChairId()
    {
        return chairId;
    }

    /**
     * Set Chair id
     * @param chairId chair id.
     */
    public void setChairId(long chairId)
    {
        this.chairId = chairId;
    }

    /**
     * Builder Chair for id.
     * @param chairId chairId to build.
     * @return Chair with id.
     */
    public Chair chairId(final long chairId)
    {
        this.chairId = chairId;
        return this;
    }

    /**
     * Get Table tableUid.
     * @return Table tableUid.
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
     * Builder Chair for tableUid.
     * @param tableUid tableUid to build.
     * @return Chair with table uid.
     */
    public Chair tableUid(final String tableUid)
    {
        this.tableUid = tableUid;
        return this;
    }

    /**
     * Get Chair uid.
     * @return Chair uid.
     */
    public String getChairUid()
    {
        return chairUid;
    }

    /**
     * Set Chair uid.
     * @param chairUid Chair uid.
     */
    public void setChairUid(String chairUid)
    {
        this.chairUid = chairUid;
    }

    /**
     * Builder Chair uid.
     * @param chairUid chairUid to build.
     * @return Chair with uid.
     */
    public Chair chairUid(final String chairUid)
    {
        this.chairUid = chairUid;
        return this;
    }

    public Boolean isOccupied()
    {
        return occupied;
    }

    public void setOccupied(Boolean occupied)
    {
        this.occupied = occupied;
    }

    public Chair occupied(final Boolean occupied)
    {
        this.occupied = occupied;
        return this;
    }

    /**
     * Get Chair xPosition.
     * @return Chair xPosition.
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * Set Chair xPosition
     * @param xPosition chair xPosition.
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Builder Chair for xPosition.
     * @param xPosition xPosition to build.
     * @return Chair with xPosition.
     */
    public Chair xPosition(final double xPosition)
    {
        this.xPosition = xPosition;
        return this;
    }

    /**
     * Get Chair yPosition.
     * @return Chair yPosition.
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * Set Chair yPosition
     * @param yPosition chair yPosition.
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Builder Chair for yPosition.
     * @param yPosition yPosition to build.
     * @return Chair with yPosition.
     */
    public Chair yPosition(final double yPosition)
    {
        this.yPosition = yPosition;
        return this;
    }

    /**
     * Get Chair angle.
     * @return Chair angle.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Set Chair angle
     * @param angle Chair angle.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Builder Chair for angle.
     * @param angle angle to build.
     * @return Chair with angle.
     */
    public Chair angle(final double angle)
    {
        this.angle = angle;
        return this;
    }

    @Override
    public String toString()
    {
        return "Chair {" +
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
