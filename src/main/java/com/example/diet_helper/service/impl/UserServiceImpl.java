package com.example.diet_helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.diet_helper.mapper.UserMapper;
import com.example.diet_helper.pojo.dto.User;
import com.example.diet_helper.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
