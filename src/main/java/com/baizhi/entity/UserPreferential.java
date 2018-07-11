package com.baizhi.entity;

import java.util.Date;

public class UserPreferential extends UserPreferentialKey {
    private Date getTime;

    private Date endTime;

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}