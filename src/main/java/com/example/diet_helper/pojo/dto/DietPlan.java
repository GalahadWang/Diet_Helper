package com.example.diet_helper.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "diet_plans")
public class DietPlan {
    @TableId(value = "plan_id", type = IdType.AUTO)
    private Integer planId;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "goal_weight")
    private BigDecimal goalWeight; // 添加了目标体重
    @TableField(value = "start_date")
    private Date startDate;       // 添加了开始日期
    @TableField(value = "end_date")
    private Date endDate;         // 添加了结束日期
    @TableField(value = "meal_name")
    private String mealName;
    private String ingredients;
    @TableField(value = "instructions")
    private String instructions;
    @TableField(value = "generate_date")
    private Date generateDate;
    @TableField(value = "is_public")
    private Boolean isPublic;


}
