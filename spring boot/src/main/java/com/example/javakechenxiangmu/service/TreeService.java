package com.example.javakechenxiangmu.service;

import com.example.javakechenxiangmu.entity.Tree;
import com.example.javakechenxiangmu.model.TreeMod;
import com.example.javakechenxiangmu.support.AccessResult;

import java.util.List;

public interface TreeService {
    AccessResult getTreeByUserId(int id);

    AccessResult planTree(TreeMod tree);

    AccessResult Water(String encryptedData, String iv, String sessionId);


    AccessResult useStepToWater(String sessionId, int step);

    AccessResult getTreeInfosessionId(String sessionId);
    AccessResult life(String sessionId);
}
