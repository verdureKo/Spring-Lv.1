package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {
    private String author;
    private String password;
    private String title;
    private String contents;

}