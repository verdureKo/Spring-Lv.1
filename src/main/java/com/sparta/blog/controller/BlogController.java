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

    @PostMapping("/log")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/logs")
    public List<BlogResponseDto> getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/log/{id}")
    public BlogResponseDto getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }   // @Requestbody를 빼면 되는것이였어

    @GetMapping("/log/contents")
    public List<BlogResponseDto> getBlogsByKeyword(String keyword) {
        return blogService.getBlogsByKeyword(keyword);
    }

    @PutMapping("/log/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {

        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/log/{id}")
    public BlogResponseDto deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.deleteBlog(id, requestDto.getPassword());
        return new BlogResponseDto(true);
    }
}