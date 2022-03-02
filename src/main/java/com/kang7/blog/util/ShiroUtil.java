package com.kang7.blog.util;

import com.kang7.blog.shiro.AccountProfile;

import org.apache.shiro.SecurityUtils;

public class ShiroUtil {
    public static AccountProfile getProfile() {
        // AccountProfile accountProfile = new AccountProfile();
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        // System.out.println(user.toString());
        return accountProfile;
    }

}
