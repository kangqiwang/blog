package com.kang7.shiro;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.kang7.common.Result;
import com.kang7.util.JwtUtils;

import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtFilter extends AuthenticatingFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse)
            throws Exception {
        // 将servletRequest强转为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 从request的头部中获取到jwt
        String jwt = request.getHeader("authorization");
        System.out.println("JwtFilter获取到的token===" + jwt);
        // 判断如果jwt为空
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        // 返回jwt
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 将servletRequest强转为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 从request的头部中获取到jwt
        String jwt = request.getHeader("authorization");
        // 判断如果jwt为空,说明没登录信息，直接交给注解进行拦截
        if (StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            // 校验jwt
            Claims claim = jwtUtils.getClaimByToken(jwt);
            // 判断如果获取到的token不存在或者已经失效
            if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
                throw new ExpiredCredentialsException("token不存在或已失效！请重新登录");
            }
            // 执行登录
            return executeLogin(servletRequest, servletResponse);
        }

    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
            ServletResponse response) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            // 获取到错误信息并返回
            Throwable cause = e.getCause() == null ? e : e.getCause();
            Result result = Result.fail(cause.getMessage());
            // 将信息转为json串
            String jsonStr = JSONUtil.toJsonStr(result);
            // 将json串输出
            httpServletResponse.getWriter().print(jsonStr);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

}