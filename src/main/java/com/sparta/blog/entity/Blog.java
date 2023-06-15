package com.sparta.blog.entity;

import com.sparta.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "blog") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false) // author로 컬럼명 변경(JS랜덤으로 되어있음ㅜㅜ)
    private String author;
    @Column(name = "password", nullable = false) // password 컬럼 추가 (할줄모룸ㅜㅜ 찾아보자)
    private String password;
    @Column(name = "title", nullable = false) // title 컬럼 추가
    private String title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Blog(BlogRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(BlogRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();   // 추가
        this.title = requestDto.getTitle();         //추가
        this.contents = requestDto.getContents();

    }
}