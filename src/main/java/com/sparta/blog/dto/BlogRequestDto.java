package com.sparta.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogRequestDto {
    private String username;
    private String password;
    private String title;
    private String contents;

    public BlogRequestDto(String username, String password, String title, String contents) {
        this.username = username;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }
}