package com.kang7.blog.shiro;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;

}
