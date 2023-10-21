package com.example.diet_helper.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")

public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    @TableField(value = "password_hash")
    private String passwordHash;
    private String email;
    private String role;
    private int weight;
    private int height;
    private int age;
    private Integer gender;
    private Date createdAt;

}
