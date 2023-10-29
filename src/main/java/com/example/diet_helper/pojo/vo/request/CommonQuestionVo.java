package com.example.diet_helper.pojo.vo.request;

import lombok.Data;

@Data
public class CommonQuestionVo {
    private Integer height;
    private Integer weight;
    private Integer age;
//    private Integer targetWeight;
    private String question;
}