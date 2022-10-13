package com.example.javakechenxiangmu.controller;

import com.example.javakechenxiangmu.service.UserService;
import com.example.javakechenxiangmu.support.AccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/aaaa")
@Controller
public class UserController {
    @Autowired
    public UserService userService;


    @RequestMapping("/loginWithCode")
    @ResponseBody
    AccessResult loginWithCode(String code){
        AccessResult result = userService.loginWithCode(code);
        return result;
    }
}
