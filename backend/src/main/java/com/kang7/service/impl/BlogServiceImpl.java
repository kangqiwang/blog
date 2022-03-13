package com.kang7.service.impl;

import com.kang7.entity.Blog;
import com.kang7.mapper.BlogMapper;
import com.kang7.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kang
 * @since 2022-03-02
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
