package com.example.javakechenxiangmu.dao;


import com.example.javakechenxiangmu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User selectUser(int id);
    
    List<User> ProcessUserInfo(int openId);

    User selectByOpenId(String openId);
    int updateByPrimaryKey(User user);

    int insertSelective(User user);
    User selectBySessionId(String sessionId);
}
