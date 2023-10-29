package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.DietMapper;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.DietPlanRequestVO;
import com.example.diet_helper.pojo.vo.response.DietPlanResponseVO;
import com.example.diet_helper.service.DietPlanService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DietPlanServiceImpl extends ServiceImpl<DietMapper, DietPlan> implements DietPlanService {
    @Resource
    private DietMapper dietPlanMapper;
    @Resource
    private GPTService gptService;

    @Override
    public String createDietPlan(DietPlanRequestVO requestVO) {
        List<String> generatedDietPlans = new ArrayList<>();
        String userInfo = "I want you to work as a nutritionist." +
                " I will provide you with all the information you need about an individual who wants to become healthier, stronger, " +
                "and fitter through a diet plan, " +
                "and your role is to create the best plan for that person based on their current situation, goals." +
                "You should use your, nutritional advice and other relevant factors to develop a plan that works for them." +
                "The following is my personal situation:" +
                "User current weight: " + requestVO.getCurrentWeight() +
                ", Goal weight: " + requestVO.getGoalWeight() +
                ", Height: " + requestVO.getHeight() +
                ", Age: " + requestVO.getAge() +
                "You must answer only in the following format:" +
                "Meal number:; Meal:;" +
                "For example: Meal 1: Breakfast\n Meal: Oatmeal with almond milk, sliced bananas, and a sprinkle of cinnamon." +
                "Generates up to 6 meals." +
                "Please format your answer strictly according to the example I gave you, including capitalization." +
                "And please don't include any other descriptive information";
        // 删除与该用户相关的所有旧饮食计划
        QueryWrapper<DietPlan> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("user_id", requestVO.getUserId());
        dietPlanMapper.delete(deleteWrapper);
        for (int day = 1; day <= 7; day++) {
            String dailyDietPlan = gptService.getFromGPT(userInfo);
            generatedDietPlans.add(dailyDietPlan);
            String[] meals = dailyDietPlan.split("Meal [0-9]+:");

            for (int i = 1; i < meals.length; i++) {
                String mealData = meals[i].trim();
                DietPlan dietPlan = getPlan(requestVO, mealData, day);
                dietPlanMapper.insert(dietPlan);
            }
        }
        System.out.println(generatedDietPlans);
        return generatedDietPlans.toString();
    }

    @NotNull
    private static DietPlan getPlan(DietPlanRequestVO requestVO, String mealData, int day) {
        String[] mealParts = mealData.split("\nMeal:");

        DietPlan dietPlan = new DietPlan();

        dietPlan.setMealName(mealParts[0].trim());  // e.g., "Breakfast", "Mid-Morning Snack", etc.
        if (mealParts.length > 1) {
            dietPlan.setMeal(mealParts[1].trim());  // e.g., "Oatmeal with almond milk..."
        }

        dietPlan.setUserId(requestVO.getUserId());
        dietPlan.setGeneratedDate(new Date());  // 设置当前日期为生成日期
        dietPlan.setWhichDay(day);  // 设置当天的日期
        return dietPlan;
    }

    @Override
    public List<DietPlan> getDietPlan(Integer userId) {
        return dietPlanMapper.findByUserId(userId);
    }

    @Override
    public List<DietPlan> getDietPlanByDay(Integer id, Integer day) {
        return dietPlanMapper.findByUserIdAndDay(id,day);
    }
}
