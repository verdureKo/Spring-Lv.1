package com.sparta.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.blog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class BlogResponseDto {
    private boolean success;
    private Long id;
    private String author;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.author = blog.getAuthor();
        this.title = blog.getTitle();
        this.contents = blog.getContents();
        this.createAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
    public BlogResponseDto(Boolean success) {
        this.success = success;
    }
}
