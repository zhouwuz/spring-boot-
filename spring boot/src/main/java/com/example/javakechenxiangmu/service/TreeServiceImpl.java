package com.example.javakechenxiangmu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.javakechenxiangmu.dao.StepMapper;
import com.example.javakechenxiangmu.dao.TreeMapper;
import com.example.javakechenxiangmu.dao.UserMapper;
import com.example.javakechenxiangmu.dao.WaterMapper;
import com.example.javakechenxiangmu.entity.Step;
import com.example.javakechenxiangmu.entity.Tree;
import com.example.javakechenxiangmu.entity.User;
import com.example.javakechenxiangmu.entity.Water;
import com.example.javakechenxiangmu.model.TreeMod;
import com.example.javakechenxiangmu.support.AccessResult;
import com.example.javakechenxiangmu.support.config.ServerConfig;
import com.example.javakechenxiangmu.support.utils.LocationUtils;
import com.example.javakechenxiangmu.support.utils.WXBizDataCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreeServiceImpl implements TreeService{
    @Autowired
    public TreeMapper treeMapper;
    @Autowired
    public WaterMapper waterMapper;
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public StepMapper stepMapper;

    @Override
    public AccessResult getTreeByUserId(int id) {
        List<Tree> trees = treeMapper.getTreeByUserId(id);
        if (trees.size() == 1){
            Map<String,Tree> map = new HashMap<>();
            map.put("tree",trees.get(0));
            return new AccessResult(0,"根据UserId查询成功",map);
        }
        else {
            return new AccessResult(-1,"根据UserId查询失败",null);
        }
    }
    @Override
    public AccessResult getTreeInfosessionId(String sessionId) {
        List<Tree> trees = treeMapper.getTreeInfosessionId(sessionId);
        if (trees.size() == 1){
            Map<String,Tree> map = new HashMap<>();
            map.put("tree",trees.get(0));
            return new AccessResult(0,"根据sessionId查询成功",map);
        }
        else {
            return new AccessResult(-1,"根据sessionId查询失败",null);
        }
    }
    @Override
    public AccessResult planTree(TreeMod tree) {

        //判断sessionId是否有效
        User user = userMapper.selectBySessionId(tree.getSessionId());
        if(user == null){
            return new AccessResult(-1,"无效用户",null);
        }

        List<Tree> trees1=treeMapper.getAroundTree(tree.getLatitude(),tree.getLongitude());
        for(Tree tree1:trees1){
            double dis = LocationUtils.getDistance(tree1.getLatitude(),tree1.getLongitude(),
                    tree.getLatitude(),tree.getLongitude());
            if(dis >= 50){
                return new AccessResult(-1,"周围已近有树",null);
            }
        }

        //判断是否已经有树了
        List<Tree> trees = treeMapper.getTreeByUserId(user.getId());
        if(trees.size() !=0){
            return new AccessResult(-1,"你已经种树了",null);
        }


        tree.setUserId(user.getId());
        int result = treeMapper.planTree(tree);
        AccessResult accessResult = new AccessResult();  //无参构造方法
        if (result == 1){
            accessResult.setErrcode(0);
            accessResult.setErrmsg("种树成功");
            accessResult.setValue(null);
//            return new AccessResult(0,"success plant",null);  有参构造方法
        }
        else {
            accessResult.setErrcode(-1);
            accessResult.setErrmsg("种树失败");
//            return new AccessResult(-1,"error plant",null);
        }
        return accessResult;
    }


    @Override
    public AccessResult useStepToWater(String sessionId, int step){
        List<Tree> trees = treeMapper.getTreeInfosessionId(sessionId);
        if(trees.size()==1) {
            //插入water表
            Water water = new Water();
            water.setUserId(trees.get(0).getUserId());
            water.setTreeId(trees.get(0).getId());
            water.setTime(new Date());
            water.setWater(step);
            waterMapper.insertWater(water);

            treeMapper.updateLife(trees.get(0).getId(), step);
                Map<String, Integer> map = new HashMap<>();
                map.put("water", step);
                return new AccessResult(0, "浇水成功", map);
            }
        else {
                return new AccessResult(-1, "无效用户", null);
            }

    }


    @Override
    public AccessResult Water(String encryptedData, String iv, String sessionId){
        User user = userMapper.selectBySessionId(sessionId);
        if(user == null){
            return new AccessResult(-1,"无法根据sessionId找到对应用户",null);
        }
        JSONObject jsonObj = this.decryptInfo(encryptedData,iv,user.getSessionKey());
        if(jsonObj == null){
            return new AccessResult(-1,"解码运动数据失败",null);
        }
        List<JSONObject> stepInfoList = JSON.parseArray(
                jsonObj.getString("stepInfoList").toString(),
                JSONObject.class);
        System.out.println(stepInfoList);
        for (JSONObject object:stepInfoList){
            Integer timestamp=(Integer) object.get("timestamp");
            Long timestamp2=timestamp.longValue();
            int step =(int) object.get("step");
            Date time =new Date(timestamp2*1000);
            SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd");
            String s=sf.format(time);

            if(isToday(s)){
                List<Step> steps=stepMapper.selectStep(user.getId());
                if(steps.size()>0){
                    stepMapper.updateStep(user.getId(),step);
                }else{
                    stepMapper.insertStep(user.getId(),step);
                }
                //使用运动步数浇水
                return userStepToWater2(user.getId(),step);
            }
        }

        return new AccessResult(-1,"没有当天的运动步数",null);
    }



    public AccessResult userStepToWater2(Integer userId, int step) {
        Date now = new Date();
        List<Water> list = waterMapper.getWater(userId,now);
        int sum = 0;
        for(Water water : list){
            sum += water.getWater();//今天总浇水量
        }
        if(step < sum){

            return new AccessResult(-1,"运动步数错误",null);
        }
        if (step == sum) {
            return new AccessResult(-1, "今天步数用完了", null);
        }
        List<Tree> trees = treeMapper.getTreeByUserId(userId);
            Water water = new Water();
            water.setUserId(userId);
            water.setTreeId(trees.get(0).getId());
            water.setTime(new Date());
            water.setWater(step - sum);
            waterMapper.insertWater(water);

            treeMapper.updateLife(trees.get(0).getId(), step - sum);
            Tree tree = treeMapper.selectByuserId(userId);
            Map<String, Integer> map = new HashMap<>();
            map.put("water", step-sum);
            map.put("life",tree.getLife());
            return new AccessResult(0, "浇水成功", map);
    }

    @Override
    public AccessResult life(String sessionId){
        User user = userMapper.selectBySessionId(sessionId);
        if (user == null){
            return new AccessResult(-1,"查找失败",null);
        }
        Tree tree = treeMapper.selectByuserId(user.getId());
        Map<String, Integer> map = new HashMap<>();
        map.put("life",tree.getLife());
        return new AccessResult(0, "查找life成功", map);
    }



    public static boolean isToday(String date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowString = sf.format(now);// 今天

        if(date.equals(nowString) ){
            return true;
        }else{
            return false;
        }
    }


    private JSONObject decryptInfo(String encryptedData, String iv, String sessionKey) {
        String appId = ServerConfig.appID;
        WXBizDataCrypt biz = new WXBizDataCrypt(appId, sessionKey);
        String data = biz.decryptData(encryptedData, iv);
        if (data == null) {
            return null;
        }
        JSONObject jsonObj = JSON.parseObject(data);
        return jsonObj;
    }


}
