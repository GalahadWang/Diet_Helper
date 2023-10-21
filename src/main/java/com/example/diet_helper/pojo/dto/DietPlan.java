package com.example.diet_helper.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName(value = "diet_plans")
public class DietPlan {

    private Integer id;
    private Integer userId;
    private String mealName;
    private String ingredients;
    private Boolean isPublic;
    private String instructions;
    private LocalDateTime generatedDate;


}
