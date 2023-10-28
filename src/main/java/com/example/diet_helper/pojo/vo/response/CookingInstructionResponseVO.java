package com.example.diet_helper.pojo.vo.response;

import lombok.Data;

@Data
public class CookingInstructionResponseVO {
    private Integer mealId;
    private String mealName;
    private String ingredients;
    private String instructions;
}
