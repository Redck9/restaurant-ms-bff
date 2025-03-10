package com.redck.restaurantmsbff.domain;

public class Schedule
{
    private String day;
    private String openTime;
    private String closeTime;

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public Schedule day(final String day)
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

    public Schedule openTime(final String openTime)
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

    public Schedule closeTime(String closeTime)
    {
        this.closeTime = closeTime;
        return this;
    }

    @Override
    public String toString()
    {
        return "Schedule { " +
                "day = " + day +
                ", openTime = " + openTime +
                ", closeTime = " + closeTime +
                " }";
    }
}

