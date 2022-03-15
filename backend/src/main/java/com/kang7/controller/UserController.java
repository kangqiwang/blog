package com.kang7.controller;

import com.kang7.common.Result;
import com.kang7.entity.User;
import com.kang7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kang
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * @RequiresAuthentication 指定需要登录认证才能进行的请求
     * @return
     */
    // @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    /**
     * 测试输入数据是否规范的校验
     * 
     * @param user
     * @return
     */
    @GetMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.success(user);
    }

}
