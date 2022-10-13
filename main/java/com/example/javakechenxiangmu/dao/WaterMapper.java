package com.example.javakechenxiangmu.dao;

import com.example.javakechenxiangmu.entity.Water;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface WaterMapper {
    void insertWater(Water water);

    List<Water> getWater(Integer userId, @Param("date") Date now);
}
