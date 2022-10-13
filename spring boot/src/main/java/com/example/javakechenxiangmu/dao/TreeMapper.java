package com.example.javakechenxiangmu.dao;

import com.example.javakechenxiangmu.entity.Tree;
import com.example.javakechenxiangmu.model.TreeMod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TreeMapper {
    List<Tree> getTreeByUserId(int id);
    int planTree(TreeMod tree);

    int updateLife(@Param("id") int treeId,@Param("life") int water);
    List<Tree> getTreeInfosessionId(String sessionId);

    List<Tree> getAroundTree(@Param("lat") double latitude,@Param("lon") double longitude);

    Tree selectByuserId(Integer userId);
}
