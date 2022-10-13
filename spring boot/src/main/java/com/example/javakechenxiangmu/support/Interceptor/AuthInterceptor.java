//package com.example.javakechenxiangmu.support.Interceptor;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.javakechenxiangmu.dao.UserMapper;
//import com.example.javakechenxiangmu.entity.User;
//import com.example.javakechenxiangmu.support.AccessResult;
//import com.example.javakechenxiangmu.support.utils.CacheService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
//public class AuthInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    CacheService cacheService;
//    @Autowired
//    public UserMapper userMapper;
//    //请求处理前调用
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception{
//        String sessionId=request.getParameter("sessionId");
//        String openId = cacheService.get("sessionId:"+sessionId);
//        if (openId!=null){
//            return true;
//        }
//        User user=userMapper.selectBySessionId(sessionId);
//        if (user!=null){
//            cacheService.add("sessionId:"+sessionId,user.getOpenId());
//            return true;
//        }
//        System.out.println("被AuthInterceptor拦截");
//        String jsonObjectStr= JSONObject.toJSONString(
//                new AccessResult(-1,"无效sessionId",null));
//        returnJson(response,jsonObjectStr);
//        return false;
//    }
//
//    private void returnJson(HttpServletResponse response, String json) throws Exception{
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        try {
//            writer = response.getWriter();
//            writer.print(json);
//
//        } catch (IOException e) {
//        } finally {
//            if (writer != null)
//                writer.close();
//        }
//    }
//
//
//
//
//}
