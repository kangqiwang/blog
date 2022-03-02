package com.kang7.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang7.blog.entity.Blog;
import com.kang7.blog.service.BlogService;
import com.kang7.blog.mapper.BlogMapper;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {
    
}
