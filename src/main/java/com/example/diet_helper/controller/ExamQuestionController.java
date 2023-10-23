package com.example.diet_helper.controller;

import com.example.diet_helper.common.R;

import com.example.diet_helper.pojo.vo.request.ExamQuestionRequestVO;
import com.example.diet_helper.pojo.vo.response.ExamQuestionResponseVO;
import com.example.diet_helper.service.ExamQuestionService;
import com.example.diet_helper.service.impl.GPT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/generate")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private GPT gpt;

    @PostMapping("/create")
    public R<List<ExamQuestionResponseVO>> createExamQuestion(@RequestBody Integer questionTotalNum) {
        try {
            List<ExamQuestionResponseVO> responseList = examQuestionService.createExamQuestion(questionTotalNum);
            return R.success(responseList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return R.error(e.getMessage());
        }
    }
    @GetMapping("/test")
    public R<ExamQuestionResponseVO> getTest(){
        try {
            ExamQuestionResponseVO responseVO = examQuestionService.createExamQuestion1();
            if (responseVO!=null){
                return R.success(responseVO);
            }else {
                return R.error("aaaaaa");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return R.error(e.getMessage());
        }
    }


    @GetMapping("/get")
    public R<ExamQuestionResponseVO> getExamQuestion(@RequestParam Integer id) {
        try {
            ExamQuestionResponseVO responseVO = examQuestionService.getExamQuestion(id);
            if (responseVO != null) {
                return R.success(responseVO);
            } else {
                return R.error("Exam question not found");
            }
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }
}
