package com.example.diet_helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.DietPlanRequestVO;


import java.util.List;


public interface DietPlanService extends IService<DietPlan>{
    String createDietPlan(DietPlanRequestVO requestVO);

    List<DietPlan> getDietPlan(Integer id);

    List<DietPlan> getDietPlanByDay(Integer id,Integer day);


}
