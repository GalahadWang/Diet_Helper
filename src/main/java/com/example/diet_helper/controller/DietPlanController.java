package com.example.diet_helper.controller;

import com.example.diet_helper.common.R;
import com.example.diet_helper.pojo.vo.request.DietPlanRequestVO;
import com.example.diet_helper.pojo.vo.response.DietPlanResponseVO;
import com.example.diet_helper.service.DietPlanService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/diet-plans")
public class DietPlanController {

    @Resource
    DietPlanService dietPlanService;

    @PostMapping("/create")
    public R<DietPlanResponseVO> createDietPlan(@RequestBody DietPlanRequestVO requestVO) {
        try {
            DietPlanResponseVO responseVO = dietPlanService.createDietPlan(requestVO);
            return R.success(responseVO);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @GetMapping("/get")
    public R<DietPlanResponseVO> getDietPlan(@RequestParam Integer id) {
        try {
            DietPlanResponseVO responseVO = dietPlanService.getDietPlan(id);
            if (responseVO != null) {
                return R.success(responseVO);
            } else {
                return R.error("Diet plan not found");
            }
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

}

