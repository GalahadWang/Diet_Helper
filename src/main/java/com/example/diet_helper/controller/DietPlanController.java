package com.example.diet_helper.controller;

import com.example.diet_helper.common.R;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.DietPlanRequestVO;
import com.example.diet_helper.pojo.vo.response.DietPlanResponseVO;
import com.example.diet_helper.service.DietPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/diet-plans")
public class DietPlanController {

    @Resource
    DietPlanService dietPlanService;

    @PostMapping("/create")
    public R<String> createDietPlan(@RequestBody DietPlanRequestVO requestVO) {
        try {
            String result = dietPlanService.createDietPlan(requestVO);
            return R.success(result);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public R<List<DietPlan>> getDietPlans(@RequestParam Integer userId) {
        List<DietPlan> dietPlans = dietPlanService.getDietPlan(userId);
        return R.success(dietPlans);
    }
    @GetMapping("/get-by-day")
    public R<List<DietPlan>> getDietPlansByDay(@RequestParam Integer userId,@RequestParam Integer day) {
        List<DietPlan> dietPlans = dietPlanService.getDietPlanByDay(userId,day);
        return R.success(dietPlans);
    }
}
