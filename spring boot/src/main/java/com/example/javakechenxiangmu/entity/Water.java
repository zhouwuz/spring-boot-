package com.example.javakechenxiangmu.entity;

import java.util.Date;

public class Water {
    private Integer id;
    private Integer userId;
    private Integer treeId;
    private Date time;
    private Integer water;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    @Override
    public String toString() {
        return "Water{" +
                "id=" + id +
                ", userId=" + userId +
                ", treeId=" + treeId +
                ", time=" + time +
                ", water=" + water +
                '}';
    }
}
