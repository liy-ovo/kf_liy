package com.baizhi.entity;

import java.util.Date;

public class Attention extends AttentionKey {
    private Date collectTime;

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}