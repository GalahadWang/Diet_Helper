package com.example.diet_helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.diet_helper.pojo.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
