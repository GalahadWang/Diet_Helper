package com.example.diet_helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.diet_helper.pojo.dto.User;
import com.example.diet_helper.pojo.vo.request.CommonQuestionVo;
import org.springframework.beans.factory.annotation.Autowired;


public interface UserService extends IService<User> {
     String WelcomeSenctence();
     String CommonQuestion(CommonQuestionVo commonQuestionVo);
}
