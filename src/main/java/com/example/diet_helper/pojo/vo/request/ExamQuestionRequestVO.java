package com.example.diet_helper.pojo.vo.request;

import lombok.Data;

@Data
public class ExamQuestionRequestVO {
    private int num;

    // Getters and setters for num
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
