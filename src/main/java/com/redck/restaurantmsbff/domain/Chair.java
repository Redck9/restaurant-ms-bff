package com.redck.restaurantmsbff.domain;

public class Chair
{
    private long chairId;

    private String tableUid;

    private String chairUid;

    private boolean occupied;

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

    public boolean isOccupied()
    {
        return occupied;
    }

    public void setOccupied(boolean occupied)
    {
        this.occupied = occupied;
    }

    public Chair occupied(final boolean occupied)
    {
        this.occupied = occupied;
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
                " }";
    }
}
