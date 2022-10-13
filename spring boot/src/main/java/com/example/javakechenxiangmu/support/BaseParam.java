package com.example.javakechenxiangmu.support;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseParam {

//    private String timestamp = CommonUtils.getCurrentTimeStamp();

    public String getParamJSON(){
        Map<String,Object> map = getDeclaredFieldsMap();
        return JSONObject.toJSONString(map);
    }

    public String getQueryString(){
        Map<String,Object> map = getDeclaredFieldsMap();
        StringBuffer queryString = new StringBuffer();
        queryString.append("?");
        for (Map.Entry<String, Object> info : map.entrySet()) {
            queryString.append((info.getKey()) + "=" + info.getValue() + "&");
        }
        String temp = queryString.toString();
        return temp.substring(0,temp.length()-1);
    }

    /**
     * 获取参数对象中声明的属性，以map形式返回
     * @return map形式的属性
     */
    private Map<String,Object> getDeclaredFieldsMap(){
        Map<String,Object> map = new HashMap<String,Object>(16);

        Field[]  fields = this.getClass().getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if(value != null){
                    String strValue = value.toString();
                    if(StringUtils.hasText(strValue)){
                        map.put((field.getName()),field.get(this));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

//    public String getTimestamp() {
//        return timestamp;
//    }
}
