package com.example.diet_helper.service;

import com.example.diet_helper.pojo.vo.request.CookingInstructionRequestVO;
import com.example.diet_helper.pojo.vo.response.CookingInstructionResponseVO;

public interface CookingService {
    void generateCookingInstructions(CookingInstructionRequestVO requestVO);

    CookingInstructionResponseVO getCookingInstructions(Integer planId);
}
