package com.kang7.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "username can't be empty")
    private String username;

    private String avatar;

    @NotBlank(message = "email can't be empty")
    @Email(message = "email format not correct")
    private String email;


    private String password;

    private Integer status;

    private LocalDateTime created;

    private LocalDateTime lastLogin;

}
