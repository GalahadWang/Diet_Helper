package com.example.diet_helper.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.diet_helper.common.R;

import com.example.diet_helper.pojo.dto.ExamQuestion;
import com.example.diet_helper.pojo.vo.request.ExamQuestionRequestVO;
import com.example.diet_helper.pojo.vo.response.ExamQuestionResponseVO;
import com.example.diet_helper.service.ExamQuestionService;
import com.example.diet_helper.service.impl.GPT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
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

    @PostMapping("/createQues")
    public R<List<ExamQuestion>> createQuestion(@RequestBody ExamQuestionRequestVO examQuestionRequestVO){
        try {
            List<ExamQuestion> responseList = new ArrayList<>();
            for(int i = 0; i<examQuestionRequestVO.getNum();i++ ){
                String GPTAnswer = gpt.generateQuestionText();
                System.out.println(GPTAnswer);
                String gpt_op_a = gpt.generateOption(GPTAnswer,"A");
                System.out.println(gpt_op_a);
                String gpt_op_b = gpt.generateOption(GPTAnswer,"B");
                System.out.println(gpt_op_b);
                String gpt_op_c = gpt.generateOption(GPTAnswer,"C");
                System.out.println(gpt_op_c);
                String gpt_op_d = gpt.generateOption(GPTAnswer,"D");
                System.out.println(gpt_op_d);
                String correct_ans = gpt.generateCorrectOption();
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setQuestionText(GPTAnswer);
                examQuestion.setOptionA(gpt_op_a);
                examQuestion.setOptionB(gpt_op_b);
                examQuestion.setOptionC(gpt_op_c);
                examQuestion.setOptionD(gpt_op_d);
                examQuestion.setCorrectOption(correct_ans);
                examQuestionService.save(examQuestion);
                responseList.add(examQuestion);
            }
            return R.success(responseList);
        }catch (Exception e){
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


    @GetMapping("/showQuestion")
    public R<List<ExamQuestion>> showQuestion(@RequestParam(name = "TotalNum", defaultValue = "5") Integer TotalNum){
        try {
//            Page<ExamQuestion> page = new Page<>(1, TotalNum); // 第一页，取totalNum个记录
//            page.addOrder(OrderItem.desc("rand()")); // 使用数据库的随机函数排序
//            List<ExamQuestion> examQuestions = examQuestionService.page(page, null).getRecords();
//            return R.success(examQuestions);
            List<ExamQuestion> examQuestions = examQuestionService.list();
            // 打乱列表
            Collections.shuffle(examQuestions);
            // 选择前TotalNum个问题
            List<ExamQuestion> randomQuestions = examQuestions.subList(0, Math.min(TotalNum, examQuestions.size()));
            return R.success(randomQuestions);
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
