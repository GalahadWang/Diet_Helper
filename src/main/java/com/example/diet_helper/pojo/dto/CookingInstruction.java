package com.example.diet_helper.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "cooking_instructions")
public class CookingInstruction {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "meal_name")
    private String mealName;
    private String ingredients;
    private String instructions;
}
