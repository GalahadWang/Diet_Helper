package com.example.diet_helper.pojo.vo.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DietPlanResponseVO {
    private Integer id;
    private Integer userId;
    private Integer goalWeight;
    private String mealName;
    private String ingredients;
    private Boolean isPublic;
    private String instructions;
    private LocalDateTime generatedDate;
}
