package com.redck.restaurantmsbff.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ScheduleDTO implements Serializable
{
    @JsonProperty("day")
    private String day;

    @JsonProperty("openTime")
    private String openTime;

    @JsonProperty("closeTime")
    private String closeTime;

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public ScheduleDTO day(final String day)
    {
        this.day = day;
        return this;
    }

    public String getOpenTime()
    {
        return openTime;
    }

    public void setOpenTime(String openTime)
    {
        this.openTime = openTime;
    }

    public ScheduleDTO openTime(final String openTime)
    {
        this.openTime = openTime;
        return this;
    }

    public String getCloseTime()
    {
        return closeTime;
    }

    public void setCloseTime(String closeTime)
    {
        this.closeTime = closeTime;
    }

    public ScheduleDTO closeTime(String closeTime)
    {
        this.closeTime = closeTime;
        return this;
    }

    @Override
    public String toString()
    {
        return "ScheduleDTO { " +
                "day = " + day +
                ", openTime = " + openTime +
                ", closeTime = " + closeTime +
                " }";
    }
}
