package com.example.diet_helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.diet_helper.pojo.dto.CookingInstruction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CookingInstructionMapper extends BaseMapper<CookingInstruction> {
}
