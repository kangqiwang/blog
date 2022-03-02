package com.kang7.blog.shiro;

import com.kang7.blog.util.JwtUtils;

import cn.hutool.core.bean.BeanUtil;

import org.apache.shiro.realm.AuthorizingRealm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

import com.kang7.blog.entity.User;
import com.kang7.blog.service.UserService;
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
/*        JwtToken jwtToken = (JwtToken) token;

//       根据token来获取到jwt
        Claims claim = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal());
        String userID = claim.getSubject();

        User user = userService.getById(Long.parseLong(userID));*/

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        System.out.println("转换成功！");
//        根据用户名查找用户
        User user = userService.getOne(new QueryWrapper<User>().eq("username",userToken.getUsername()));

//        System.out.println(user.toString());


        if (user == null){
            throw new UnknownAccountException("账户不存在！");
        }

        if (user.getStatus() == -1){
            throw new LockedAccountException("账户被锁定！");
        }
//        根据id生成token
        String jwtToken = jwtUtils.generateToken(user.getId());

        AccountProfile profile = new AccountProfile();
//        将获取到的用户信息copy到新的映射中
        BeanUtil.copyProperties(user,profile);


        System.out.println("最后一步了" + SecurityUtils.getSubject().getPrincipal());
//        System.out.println("获取到的jwtToken" + jwtToken);
//        返回用户信息
        return new SimpleAuthenticationInfo(profile,user.getPassword(),"");
    }

}
