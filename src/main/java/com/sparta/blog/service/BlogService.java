package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;

@Component  // Bean객체로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {

        this.blogRepository = blogRepository;   
        // new 하지않고, 파라미터로 받아오기
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        // DB 저장
        Blog saveBlog = blogRepository.save(blog);

        // Entity -> ResponseDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);

        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlogs() {
        // DB 조회
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public List<BlogResponseDto> getBlogsByKeyword(String keyword) {
        return blogRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto getBlog(Long id, BlogRequestDto requestDto) {
        Blog blog  =  findBlog(id);
        BlogResponseDto blogResponseDto =  new BlogResponseDto(blog);
        return blogResponseDto;
    }

    @Transactional  // 변경감지: 영속성
    public Long updateBlog(Long id, BlogRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = findBlog(id);
        // blog 내용 수정
        if(blog.getPassword().equals(requestDto.getPassword())){
            blog.update(requestDto);
            return id;
        }else{
            throw new InputMismatchException("비밀번호를 다시 입력해주세요");
        }
    }

    public Long deleteBlog(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = findBlog(id);
        blogRepository.delete(blog);
        return id;
    }

    // 공통으로 사용하는 코드는 아래위치하는 것이 좋다
    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}