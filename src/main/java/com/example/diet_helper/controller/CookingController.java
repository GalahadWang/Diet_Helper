package com.example.diet_helper.controller;

import com.example.diet_helper.common.R;
import com.example.diet_helper.mapper.CookingInstructionMapper;
import com.example.diet_helper.mapper.DietMapper;
import com.example.diet_helper.pojo.dto.DietPlan;
import com.example.diet_helper.pojo.vo.request.CookingInstructionRequestVO;
import com.example.diet_helper.pojo.vo.response.CookingInstructionResponseVO;
import com.example.diet_helper.service.CookingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/cooking")
public class CookingController {
    @Resource
    private CookingService cookingService;
    @Resource
    private DietMapper dietMapper;
    @PostMapping("/generate-instructions")
    public R<String> generateInstructions(@RequestBody CookingInstructionRequestVO requestVO) {
        try {
            cookingService.generateCookingInstructions(requestVO);
            return R.success("success");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }
    @GetMapping("/instructions")
    public R<CookingInstructionResponseVO> getCookingInstructions(@RequestParam Integer planId) {
        CookingInstructionResponseVO responseVO = cookingService.getCookingInstructions(planId);
        return R.success(responseVO);}
}
