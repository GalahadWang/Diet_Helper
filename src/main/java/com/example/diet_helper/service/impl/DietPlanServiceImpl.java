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
import java.util.Date;

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
                ", Age: " + requestVO.getAge();

        // 获取饮食建议
        String generatedDietPlan = gptService.getDietAdvice(userInfo);

        // 将生成的饮食计划与请求中的其他信息一起存储到数据库
        DietPlan dietPlan = new DietPlan();
        BeanUtils.copyProperties(requestVO, dietPlan);
//        dietPlan.setInstructions(generatedDietPlan);

        dietPlan.setGeneratedDate(new Date());
        dietPlanMapper.insert(dietPlan);

        DietPlanResponseVO responseVO = new DietPlanResponseVO();
        BeanUtils.copyProperties(dietPlan, responseVO);
        return responseVO;
    }

    @Override
    public DietPlanResponseVO getDietPlan(Integer id) {
        return null;
    }
}