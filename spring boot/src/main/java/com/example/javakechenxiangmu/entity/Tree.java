package com.example.javakechenxiangmu.entity;

import java.util.Date;

public class Tree {
    private Integer id;
    private User user;
    private String name;
    private Integer life;
    private Double latitude;
    private Double longitude;
    private Date time;
    private int userId;

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", life=" + life +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", time=" + time +
                ", userId=" + userId +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
