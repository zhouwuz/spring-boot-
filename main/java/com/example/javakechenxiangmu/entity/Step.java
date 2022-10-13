package com.example.javakechenxiangmu.entity;

import java.util.Date;

public class Step {
    private Integer id;
    private Integer userId;
    private Integer step;
    private Date date;

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

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", userId=" + userId +
                ", step=" + step +
                ", date=" + date +
                '}';
    }
}
