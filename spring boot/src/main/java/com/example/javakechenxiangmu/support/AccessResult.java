package com.example.javakechenxiangmu.support;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

public class AccessResult implements Serializable {

    private static final long serialVersionUID = 15009794820L;
    private Integer errcode;
    private String errmsg;
    private Map value;

    public Map getValue() {
        return value;
    }

    public void setValue(Map value) {
        this.value = value;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int code) {
        this.errcode = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String message) {
        this.errmsg = message;
    }

    public AccessResult(){}

    public AccessResult(int code, String message, Map value) {
        this.value = value;
        this.errcode = code;
        this.errmsg = message;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
