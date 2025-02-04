package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.ExamQuestionMapper;
import com.example.diet_helper.pojo.dto.ExamQuestion;
import com.example.diet_helper.pojo.vo.request.ExamQuestionRequestVO;
import com.example.diet_helper.pojo.vo.response.ExamQuestionResponseVO;
import com.example.diet_helper.service.ExamQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {
    @Resource
    private ExamQuestionMapper examQuestionMapper;
    @Resource
    private GPT gpt;
    @Resource
    private GPTService gptService;

    public ExamQuestionResponseVO createExamQuestion1(){

        String GPTAnswer = gpt.generateQuestionText();
        System.out.println(GPTAnswer);
        String gpt_op_a = gpt.generateOption(GPTAnswer,"A");
        String gpt_op_b = gpt.generateOption(GPTAnswer,"B");
        String gpt_op_c = gpt.generateOption(GPTAnswer,"C");
        String gpt_op_d = gpt.generateOption(GPTAnswer,"D");
        String correct_ans = gpt.generateCorrectOption();
        ExamQuestionResponseVO examQuestionResponseVO = new ExamQuestionResponseVO();
        examQuestionResponseVO.setQuestionText(GPTAnswer);
        examQuestionResponseVO.setOptionA(gpt_op_a);
        examQuestionResponseVO.setOptionB(gpt_op_b);
        examQuestionResponseVO.setOptionC(gpt_op_c);
        examQuestionResponseVO.setOptionD(gpt_op_d);
        examQuestionResponseVO.setCorrectOption(correct_ans);
        return examQuestionResponseVO;
    }

    @Override
    public List<ExamQuestionResponseVO> createExamQuestion(Integer questionTotalNum) {
        return null;
    }

    @Override
    public List<ExamQuestionResponseVO> createExamQuestion(ExamQuestionRequestVO examQuestionRequestVO) {
        int numQuestionsToGenerate = examQuestionRequestVO.getNum();

        // Create a list to store the generated questions
        List<ExamQuestionResponseVO> responseList = new ArrayList<>();

        // Loop to generate the specified number of questions
        for (int i = 0; i < numQuestionsToGenerate; i++) {
            String GPTAnswer = gpt.generateQuestionText();
            System.out.println(GPTAnswer);
            String gpt_op_a = gpt.generateOption(GPTAnswer,"A");
            String gpt_op_b = gpt.generateOption(GPTAnswer,"B");
            String gpt_op_c = gpt.generateOption(GPTAnswer,"C");
            String gpt_op_d = gpt.generateOption(GPTAnswer,"D");
            String correct_ans = gpt.generateCorrectOption();
            ExamQuestionResponseVO examQuestionResponseVO = new ExamQuestionResponseVO();
            examQuestionResponseVO.setQuestionText(GPTAnswer);
            examQuestionResponseVO.setOptionA(gpt_op_a);
            examQuestionResponseVO.setOptionB(gpt_op_b);
            examQuestionResponseVO.setOptionC(gpt_op_c);
            examQuestionResponseVO.setOptionD(gpt_op_d);
            examQuestionResponseVO.setCorrectOption(correct_ans);
        }

        return responseList;
    }

    @Override
    public ExamQuestionResponseVO getExamQuestion(Integer id) {
        // Get a specific exam question by ID and return it
        ExamQuestion examQuestion = examQuestionMapper.selectById(id);
        ExamQuestionResponseVO responseVO = new ExamQuestionResponseVO();
        BeanUtils.copyProperties(examQuestion, responseVO);
        return responseVO;
    }
}
