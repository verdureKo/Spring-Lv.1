package com.sparta.blog.dto;

import com.sparta.blog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.createAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}
