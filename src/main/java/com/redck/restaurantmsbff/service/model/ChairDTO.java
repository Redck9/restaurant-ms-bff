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
    private boolean occupied;

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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public ChairDTO occupied(final boolean occupied)
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
