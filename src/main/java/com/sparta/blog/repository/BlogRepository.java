package com.sparta.blog.repository;


import com.sparta.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();
//    List<Blog> findAllByUsername(String username);
    List<Blog> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);  // 콘텐츠의 키워드로 글찾기 내림차순정렬 

}