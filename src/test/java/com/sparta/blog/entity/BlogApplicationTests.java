package com.sparta.blog.entity;

import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {
	}
	@PersistenceContext
	EntityManager em;

	@Autowired
	BlogRepository blogRepository;

	@Test
	@Transactional
	@Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
	@DisplayName("Test Log 생성 성공")
	void test1() {
		Blog blog = new Blog();
		blog.setUsername("푸름");
		blog.setContents("@Transactional 테스트 중!");
		blog.setPassword("1234");
		blog.setTitle("제목");

		em.persist(blog);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
	}

	@Test
	@Disabled	// 주석처리하지않아도 ignore해준다
	@DisplayName("Test Log 생성 실패")
	void test2() {
		Blog blog = new Blog();
		blog.setUsername("푸름");
		blog.setContents("@Transactional 테스트 중!");
		blog.setPassword("1234");
		blog.setTitle("Title");

		em.persist(blog);
		// @Transactional 환경이 아니기때문에 Log 생성 실패
	}
}
