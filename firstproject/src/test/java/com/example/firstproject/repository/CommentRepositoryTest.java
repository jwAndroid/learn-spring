package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest // jpa 테스트 코드
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // 입력 데이터 준비

        // 실제 수행 -> 아티클 아이디로 댓글 모두 조회하기
        List<Comment> comments = commentRepository.findByArticleId(1L);

        // 예상하기

        // 검증
    }

    @Test
    void findByNickname() {
    }
}
