package com.example.diet_helper.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.diet_helper.common.R;
import com.example.diet_helper.pojo.User;
import com.example.diet_helper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public R<User> login(HttpServletRequest request, @RequestBody User user, HttpServletResponse response){

        System.out.println(user);

        //1 Encrypt the password password submitted on the page with md5 encryption
        String passwordHash = user.getPasswordHash();

        //2 Querying the database
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());
        wrapper.eq("password_hash",user.getPasswordHash());
        User user_1 = userService.getOne(wrapper);

        //3 If no query is found, a login failure result is returned.
        if(user_1 == null){
            response.setStatus(403);
            return R.error("No relevant user, login failed");
        }

        //4 Password comparison, if it does not match then return a login failure result
        if(!user_1.getPasswordHash().equals(user.getPasswordHash())){
            response.setStatus(402);
            return R.error("Wrong password, login failed");
        }


        //5、Login successfully, store the employee id into the session and return the login success result
        request.getSession().setAttribute("User",user_1.getId());
        return R.success(user_1);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("User");
        return R.success("Exit successfully");
    }

    @PostMapping("/Signup")
    public R<User> signup(@RequestBody User cond, HttpServletResponse response) {
        System.out.println(cond);

        // Check if the email is already in use
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", cond.getEmail());

        User exist = userService.getOne(wrapper);

        if (exist != null) {
            System.out.println(cond);
            return R.error("Email has been used");
        }
        // Set a default value for username
        if (cond.getEmail() != null) {
//            cond.setUsername("DefaultUsername"); // 设置默认用户名
            userService.save(cond);
            System.out.println(cond);

            return R.success(cond);
        }


        response.setStatus(403);
        return R.error("Failed to register");
    }


}
