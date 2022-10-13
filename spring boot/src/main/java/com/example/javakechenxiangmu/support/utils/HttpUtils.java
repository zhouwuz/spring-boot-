package com.example.javakechenxiangmu.support.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.javakechenxiangmu.support.AccessResult;
import com.example.javakechenxiangmu.support.BaseParam;
import com.example.javakechenxiangmu.support.config.ConstantValue;
import com.example.javakechenxiangmu.support.config.TreeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


/**
 * @Author:
 * @Date:
 * @Description:
 * @Version: 1.0
 */
@Component
public class HttpUtils {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送POST请求
     * @param url 访问URL
     * @param queryParam 查询参数
     * @return 访问结果对象
     */
    public AccessResult sendPostRequest(String url, BaseParam queryParam){

        //请求头设置
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        //设置 签名 查询参数 访问令牌
        HttpEntity<String> requestEntity = new HttpEntity<String>(queryParam.getParamJSON(), requestHeaders);

        //发起请求
        String strResult = restTemplate.postForObject(url, requestEntity, String.class);

        //解析返回字符串
        JSONObject jsonObject = JSONObject.parseObject(strResult);
        return getResult(jsonObject);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     */
    public AccessResult sendGetRequest(String url, BaseParam queryParam){
        //请求头设置
        HttpHeaders requestHeaders = new HttpHeaders();
        //设置 签名 查询参数 访问令牌
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        //发起请求
        ResponseEntity<String> strResult = restTemplate.exchange(url + queryParam.getQueryString(), HttpMethod.GET, requestEntity, String.class);
        //解析返回字符串
        JSONObject jsonObject = JSONObject.parseObject(strResult.getBody());
        return getResult(jsonObject);
    }

    /**
     * 发起请求的时候request头部的设置
     * @param requestHeaders 请求头
     * @param signature 签名
     * @param timeStamp 时间戳
     * @param token token值
     */
    private void setHeader(HttpHeaders requestHeaders, String timeStamp){
        requestHeaders.setAccept(Arrays.asList(MediaType.ALL));
        requestHeaders.add(ConstantValue.HTTP_TIME_STAMP_KEY, timeStamp);
    }

    /**
     * 获取组装后的返回对象
     * @param jsonObject
     * @return
     */
    private AccessResult getResult(JSONObject jsonObject){
        AccessResult accessResult = new AccessResult();

        Object err = jsonObject.get(ConstantValue.RESULT_CODE_NAME);
        Object msg = jsonObject.get(ConstantValue.RESULT_MESSAGE_NAME);

        if(err != null && msg != null){
            accessResult.setErrcode(Integer.valueOf(err.toString()));
            accessResult.setErrmsg(String.valueOf(msg));
        }else{
            accessResult.setErrcode(TreeCode.SUCCESS_CODE);
            accessResult.setErrmsg(TreeCode.SUCCESS_MESSAGE);
            accessResult.setValue(jsonObject);

        }
//
//        Object obj = jsonObject.get(ConstantValue.RESULT_VALUE_NAME);
//        if (obj instanceof Map){
//            accessResult.setValue((Map) jsonObject.get(ConstantValue.RESULT_VALUE_NAME));
//        } else if (obj != null){
//            accessResult.setValue(ImmutableMap.of("List",obj));
//        }
        return accessResult;
    }
}
