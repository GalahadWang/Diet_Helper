package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.UserMapper;
import com.example.diet_helper.pojo.dto.User;
import com.example.diet_helper.pojo.vo.request.CommonQuestionVo;
import com.example.diet_helper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private GPT gpt;

    public String WelcomeSenctence(){
        String GPTAnswer = gpt.EncourageSenctence();
        System.out.println(GPTAnswer);
        return GPTAnswer;
    }

    public String CommonQuestion(CommonQuestionVo commonQuestionVo){
        String GPTAnswer = gpt.AnswerQuestion(commonQuestionVo);
        System.out.println(GPTAnswer);
        return GPTAnswer;
    }

}
