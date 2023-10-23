package com.example.diet_helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.diet_helper.pojo.dto.ExamQuestion;
import com.example.diet_helper.pojo.vo.request.ExamQuestionRequestVO;
import com.example.diet_helper.pojo.vo.response.ExamQuestionResponseVO;

import java.util.List;


public interface ExamQuestionService extends IService<ExamQuestion>{
    List<ExamQuestionResponseVO> createExamQuestion(Integer questionTotalNum);
    ExamQuestionResponseVO getExamQuestion(Integer id);
    ExamQuestionResponseVO createExamQuestion1();

}
