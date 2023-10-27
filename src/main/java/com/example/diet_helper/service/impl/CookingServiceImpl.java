package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.DietMapper;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.CookingInstructionRequestVO;
import com.example.diet_helper.pojo.vo.response.CookingInstructionResponseVO;
import com.example.diet_helper.service.CookingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class CookingServiceImpl extends ServiceImpl<DietMapper, DietPlan> implements CookingService{

    @Resource
    private DietMapper dietMapper;

    @Resource
    private GPTService gptService;

    @Override
    public void generateCookingInstructions(CookingInstructionRequestVO requestVO) {
        Integer planId = requestVO.getPlanId();
        // 使用planId找到相应的DietPlan
        DietPlan dietPlan = dietMapper.selectById(planId);
        if (dietPlan == null) {
            throw new RuntimeException("DietPlan with given planId not found.");
        }
        String meal = dietPlan.getMeal();

        String prompt = "As a cooking instruction generator, I want you to create a cooking instruction for a meal. " +
                "Here is the meal I would like you to generate instructions for: " + meal +
                "You must reply in the following format: Ingredients: Instructions:" +
                "Please do not include any information other than the format";

        // 向GPT请求制作步骤
        String instructionsResponse = gptService.getFromGPT(prompt);
        System.out.println(instructionsResponse);

        // 从GPT返回的指令中解析ingredients和instructions
        String[] parts = instructionsResponse.split("Instructions:");
        String ingredients = parts[0].replace("Ingredients:", "").trim();
        String instructions = parts[1].trim();

        // 更新找到的DietPlan
        dietPlan.setIngredients(ingredients);
        dietPlan.setInstructions(instructions);
        dietMapper.updateById(dietPlan);
    }

    @Override
    public CookingInstructionResponseVO getCookingInstructions(CookingInstructionRequestVO requestVO) {
        // 使用DietMapper查询DietPlan
        DietPlan dietPlan = dietMapper.selectById(requestVO.getPlanId());

        // 如果dietPlan不存在，返回null或抛出一个异常
        if (dietPlan == null) {
            return null;
        }

        // 将检索到的数据映射到CookingInstructionResponseVO对象中
        CookingInstructionResponseVO responseVO = new CookingInstructionResponseVO();
        responseVO.setMealId(dietPlan.getPlanId());
        responseVO.setMealName(dietPlan.getMealName());
        responseVO.setIngredients(dietPlan.getIngredients());
        responseVO.setInstructions(dietPlan.getInstructions());

        return responseVO;
    }
}

