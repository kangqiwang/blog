package com.kang7.blog.common.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "username not null")
    private String username;

    @NotBlank(message = "passwork not null")
    private String password;
}
