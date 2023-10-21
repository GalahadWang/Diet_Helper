package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.DietMapper;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.DietPlanRequestVO;
import com.example.diet_helper.pojo.vo.response.DietPlanResponseVO;
import com.example.diet_helper.service.DietPlanService;
import org.springframework.stereotype.Service;

@Service
public class DietPlanServiceImpl extends ServiceImpl<DietMapper, DietPlan> implements DietPlanService {

    @Override
    public DietPlanResponseVO createDietPlan(DietPlanRequestVO requestVO) {
        return null;
    }

    @Override
    public DietPlanResponseVO getDietPlan(Integer id) {
        return null;
    }
}
