package com.example.diet_helper.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "diet_plans")
public class DietPlan {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "meal_name")
    private String mealName;
    @TableField(value = "is_public")
    private Boolean isPublic;
    private String meal;
    @TableField(value = "generated_date")
    private Date generatedDate;
    @TableField(value = "which_day")
    private Integer whichDay;


}