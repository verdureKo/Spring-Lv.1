package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/logs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/logs")
    public List<BlogResponseDto> getBlogs() {
        return blogService.getBlogs();
    }

    // 튜터님 피드백: path의 {id}로 글 1개인 것을 알 수 있다, URI 컨벤션을 참고하면 단수<복수
    @GetMapping("/logs/{id}")
    public BlogResponseDto getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/logs/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {

        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/logs/{id}")
    public BlogResponseDto deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.deleteBlog(id, requestDto.getPassword());
        return new BlogResponseDto(true);
    }
}