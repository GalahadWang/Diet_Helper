package com.example.diet_helper.pojo.vo.request;

import lombok.Data;

@Data
public class DietPlanRequestVO {
    private Integer userId;
    private String mealName;
    private String ingredients;
    private Boolean isPublic;
    private String instructions;
}
