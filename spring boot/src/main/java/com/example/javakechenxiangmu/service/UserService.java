package com.example.javakechenxiangmu.service;

import com.example.javakechenxiangmu.entity.User;
import com.example.javakechenxiangmu.support.AccessResult;

public interface UserService {
    AccessResult loginWithCode(String code);
//    User getUserByOpenId(String openId);
}
