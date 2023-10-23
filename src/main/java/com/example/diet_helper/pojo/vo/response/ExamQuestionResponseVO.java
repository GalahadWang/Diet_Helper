package com.example.diet_helper.pojo.vo.response;

import lombok.Data;

@Data
public class ExamQuestionResponseVO {
    private Integer id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;
}
