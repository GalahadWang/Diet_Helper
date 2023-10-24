package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.DietMapper;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.DietPlanRequestVO;
import com.example.diet_helper.pojo.vo.response.DietPlanResponseVO;
import com.example.diet_helper.service.DietPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DietPlanServiceImpl extends ServiceImpl<DietMapper, DietPlan> implements DietPlanService {
    @Resource
    private DietMapper dietPlanMapper;
    @Resource
    private GPTService gptService;

    @Override
    public DietPlanResponseVO createDietPlan(DietPlanRequestVO requestVO) {
        String userInfo = "I want you to work as a nutritionist." +
                " I will provide you with all the information you need about an individual who wants to become healthier, stronger, " +
                "and fitter through a diet plan, " +
                "and your role is to create the best plan for that person based on their current situation, goals." +
                "You should use your, nutritional advice and other relevant factors to develop a plan that works for them." +
                "The following is my personal situation:" +
                "User current weight: " + requestVO.getCurrentWeight() +
                ", Goal weight: " + requestVO.getGoalWeight() +
                ", Height: " + requestVO.getHeight() +
                ", Age: " + requestVO.getAge()+
                "You must answer only in the following format:" +
                " mealname:; ingredients:; instructions:;" +
                "For example, Meal 1: Breakfast\n" +
                "Ingredients: \n" +
                "- 2 eggs\n" +
                "- 1 avocado\n" +
                "- 2 slices of whole wheat toast\n" +
                "- 1 small banana\n" +
                "Instructions:\n" +
                "1. Scramble the eggs in a non-stick pan over medium heat.\n" +
                "2. Slice the avocado and mash it onto the toast.\n" +
                "3. Serve the eggs and toast with a side of sliced banana." +
                "And please don't include any other descriptive information";

        String generatedDietPlan = gptService.getFromGPT(userInfo);

        // 分割文本以获取每个餐次的数据
        String[] mealSections = generatedDietPlan.split("Meal \\d+: ");
        List<DietPlan> dietPlanList = new ArrayList<>();

        for (String mealSection : mealSections) {
            if (!mealSection.trim().isEmpty()) {
                String mealName = mealSection.substring(0, mealSection.indexOf("\n")).trim();
                String ingredients = mealSection.substring(mealSection.indexOf("Ingredients:") + 12, mealSection.indexOf("Instructions:")).trim();
                String instructions = mealSection.substring(mealSection.indexOf("Instructions:") + 13).trim();

                DietPlan dietPlan = new DietPlan();
                dietPlan.setMealName(mealName);
                dietPlan.setIngredients(ingredients);
                dietPlan.setInstructions(instructions);

                // 其他属性
                dietPlan.setUserId(requestVO.getUserId());
                dietPlan.setGeneratedDate(new Date());  // 设置当前日期为生成日期

                dietPlanList.add(dietPlan);
            }
        }

        for (DietPlan dietPlan : dietPlanList) {
            dietPlanMapper.insert(dietPlan);
        }

        DietPlanResponseVO responseVO = new DietPlanResponseVO();
        // 这里只返回最后一个DietPlan的属性
        BeanUtils.copyProperties(dietPlanList.get(dietPlanList.size() - 1), responseVO);
        return responseVO;//其实也可以返回null
    }

    @Override
    public List<DietPlan> getDietPlan(Integer userId) {
        return dietPlanMapper.findByUserId(userId);
    }
}
