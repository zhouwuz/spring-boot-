package com.example.javakechenxiangmu.service;

import com.example.javakechenxiangmu.dao.UserMapper;
import com.example.javakechenxiangmu.entity.User;
import com.example.javakechenxiangmu.model.Code2Session;
import com.example.javakechenxiangmu.support.AccessResult;
import com.example.javakechenxiangmu.support.config.ConstantValue;
import com.example.javakechenxiangmu.support.config.ServerConfig;
import com.example.javakechenxiangmu.support.config.TreeCode;

import com.example.javakechenxiangmu.support.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private HttpUtils httpUtils;


    @Override
    public AccessResult loginWithCode(String code) {
        AccessResult result = getSessionWx(code); // 从微信服务器获取openid等信息
        if( result.getErrcode() != 0) {
            return result;
        }

        // 生成 sessionId
        String openId = result.getValue().get("openid").toString();
        String sessionKey = result.getValue().get("session_key").toString();
        String sessionId = Md5(sessionKey);//Md5哈希散列的算法

        Map<String, String> map = new HashMap<>();
        map.put("sessionId", sessionId);

        //处理用户信息，将信息插入数据库，有就更新，没有就插入。
        ProcessUserInfo(openId, sessionKey, sessionId);
        return new AccessResult(TreeCode.SUCCESS_CODE, TreeCode.SUCCESS_MESSAGE, map);
    }


    private AccessResult getSessionWx(String code){
        Code2Session codeParam = new Code2Session();
        codeParam.setAppid(ServerConfig.appID);
        codeParam.setSecret(ServerConfig.appSecret);
        codeParam.setJs_code(code);
        codeParam.setGrant_type(ConstantValue.AUTHORIZATION_CODE);
        return httpUtils.sendGetRequest(ServerConfig.code2Session, codeParam);
    }

    public String Md5(String text)
    {
        String str = DigestUtils.md5DigestAsHex(text.getBytes());
        return str;
    }
    private void ProcessUserInfo(String openId, String sessionKey, String sessionId) {
        User user = userMapper.selectByOpenId(openId);
        if(user !=null){
            user.setSessionKey(sessionKey);
            user.setSessionId(sessionId);
            userMapper.updateByPrimaryKey(user);
        }else{
            user = new User();
            user.setDate(new Date());
            user.setOpenId(openId);
            user.setSessionKey(sessionKey);
            user.setSessionId(sessionId);
            userMapper.insertSelective(user);
        }


    }
}
