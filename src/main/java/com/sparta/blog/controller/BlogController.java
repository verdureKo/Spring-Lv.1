package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
        // new 하지않고, 외부에서 만든 파라미터로 전달
    }

    @PostMapping("/logs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/logs")
    public List<BlogResponseDto> getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/logs/contents")
    public List<BlogResponseDto> getBlogsByKeyword(String keyword) {
        return blogService.getBlogsByKeyword(keyword);
    }

    @PutMapping("/logs/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/logs/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id);
    }
}