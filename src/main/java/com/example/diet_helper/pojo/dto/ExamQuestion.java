package com.example.diet_helper.pojo.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "exam-questions")
public class ExamQuestion {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "question_text")
    private String questionText;
    @TableField(value = "option_a")
    private String optionA;
    @TableField(value = "option_b")
    private String optionB;
    @TableField(value = "option_c")
    private String optionC;
    @TableField(value = "option_d")
    private String optionD;
    @TableField(value = "correct_option")
    private String correctOption;
}
