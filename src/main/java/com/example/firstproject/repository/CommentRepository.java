package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    // Query 어노테이션으로 쿼리
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    // article_id 컬럼 네임 articleId 받을 변수명
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    // xml 로 쿼리
    List<Comment> findByNickname(String nickname);
}
