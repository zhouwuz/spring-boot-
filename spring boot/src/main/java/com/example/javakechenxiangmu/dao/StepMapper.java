package com.example.javakechenxiangmu.dao;

import com.example.javakechenxiangmu.entity.Step;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StepMapper {
    List<Step> selectStep(int userId);//查询今天运动步数

    int updateStep(int userId, int step);

    int insertStep(int userId, int step);
}
