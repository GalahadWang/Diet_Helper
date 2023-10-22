package com.example.diet_helper.pojo.vo.request;

import lombok.Data;

@Data
public class DietPlanRequestVO {
    private Integer userId;
    private Integer currentWeight;
    private Integer goalWeight;
    private Integer height;
    private Integer age;
}
