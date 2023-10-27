package com.example.diet_helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.diet_helper.pojo.dto.DietPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DietMapper extends BaseMapper<DietPlan> {
    @Select("SELECT * FROM diet_plans WHERE user_id = #{userId}")
    List<DietPlan> findByUserId(Integer userId);
    @Select("SELECT * FROM diet_plans WHERE user_id = #{userId} AND which_day = #{day}")
    List<DietPlan> findByUserIdAndDay(Integer userId, Integer day);
}
