//package com.example.javakechenxiangmu.support.Interceptor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(abc())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/springmvc/user/loginWithCode");
//    }
//
//
//    @Bean
//    public AuthInterceptor abc(){
//        return new AuthInterceptor();
//    }
//}
