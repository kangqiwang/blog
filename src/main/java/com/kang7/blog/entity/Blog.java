package com.kang7.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain =true)
@TableName("blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @NotBlank(message = "please fill in title")
    private String title;

    @NotBlank(message = "description can't be empty")
    private String description;

    @NotBlank(message = "content can't be empty")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime created;

    private Integer status;

}
