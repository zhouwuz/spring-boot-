package com.example.javakechenxiangmu.controller;



import com.example.javakechenxiangmu.model.TreeMod;
import com.example.javakechenxiangmu.service.TreeService;
import com.example.javakechenxiangmu.support.AccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class TreeController {
    @Autowired
    public TreeService treeService;

    @RequestMapping("/getTreeByUserId")
    @ResponseBody
    public AccessResult getTreeByUserId(@RequestParam("userId") int id) {
        return treeService.getTreeByUserId(id);
    }

    @PostMapping("/plantTree")
    @ResponseBody
    public AccessResult plantTree(TreeMod tree) //驼峰命名法入参为对象
    {

        AccessResult result = treeService.planTree(tree);
        return result;
    }

    @PostMapping("/waterTree")
    @ResponseBody
    AccessResult waterTree(String encryptedData, String iv, String sessionId){
        AccessResult result = treeService.Water(encryptedData,iv,sessionId);
        return result;
    }

    @RequestMapping("/getTreeInfosessionId")
    @ResponseBody
    public AccessResult getTreeInfosessionId(String sessionId) {
        return treeService.getTreeInfosessionId(sessionId);
    }
    @PostMapping("/useStepToWater")
    @ResponseBody
    AccessResult useStepToWater(String sessionId,int step){
        AccessResult result = treeService.useStepToWater(sessionId,step);
        return result;
    }

    @RequestMapping("/hello/{name}/{age}")
    @ResponseBody
    String hello(@PathVariable("name") String name , @PathVariable("age") int age){
        return "hello " + name + ", the age is" + age;
    }

    @PostMapping("/life")
    @ResponseBody
    AccessResult life(String sessionId){
        AccessResult result = treeService.life(sessionId);
        return result;
    }

}
