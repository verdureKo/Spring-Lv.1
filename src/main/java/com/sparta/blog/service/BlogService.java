package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // Bean객체로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;    // new 하지않고, 파라미터로 받아오기
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto); // RequestDto -> Entity

        Blog saveBlog = blogRepository.save(blog);

        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);  // Entity -> ResponseDto

        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(BlogResponseDto::new)
                .toList();
    }

    public List<BlogResponseDto> getBlogsByKeyword(String keyword) {
        return blogRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream()
                .map(BlogResponseDto::new)
                .toList();
    }

    public BlogResponseDto getBlog(Long id) {
        Blog blog  =  findBlog(id);
        BlogResponseDto blogResponseDto =  new BlogResponseDto(blog);
        return blogResponseDto;
    }

    @Transactional  // 변경감지: 영속성
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        Blog blog = findBlog(id);

        blog.checkPassword(requestDto.getPassword());

        blog.setTitle(requestDto.getTitle());
        blog.setAuthor(requestDto.getAuthor());
        blog.setContents(requestDto.getContents());

        return new BlogResponseDto(blog);
    }

    public void deleteBlog(Long id, String password) {
        Blog blog = findBlog(id);

        blog.checkPassword(password);
        blogRepository.delete(blog);
    }

    // 공통으로 사용하는 코드는 아래위치하는 것이 좋다
    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 Log는 존재하지 않습니다.")
        );
    }

}