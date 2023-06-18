package com.sparta.blog.entity;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter // DB와 직결되어있는 Entity클래스는 setter를 주의해 사용해야한다
@Table(name = "blog") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Blog(BlogRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void checkPassword(String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}