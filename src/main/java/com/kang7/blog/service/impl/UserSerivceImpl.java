package com.kang7.blog.service.impl;
import org.springframework.stereotype.Service;
import com.kang7.blog.entity.User;
import com.kang7.blog.mapper.UserMapper;
import com.kang7.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class UserSerivceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
}
