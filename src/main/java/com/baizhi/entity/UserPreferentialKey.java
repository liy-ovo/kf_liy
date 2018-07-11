package com.baizhi.entity;

public class UserPreferentialKey {
    private String userId;

    private String preId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPreId() {
        return preId;
    }

    public void setPreId(String preId) {
        this.preId = preId == null ? null : preId.trim();
    }
}