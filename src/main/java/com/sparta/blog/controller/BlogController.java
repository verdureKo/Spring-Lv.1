package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// 튜터님의 개인 과제 피드백:
// url path 에 /logs, /log 이렇게 2가지가 있는데 /logs 로 통일해서 사용하는걸 추천드립니다.
// /logs/{id} 로 호출하는것 자체가 목록에서 id에 해당하는 1개를 뽑아온다고 봐주면 좋아요(컨벤션)
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