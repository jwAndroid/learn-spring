package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자 자동완성
@ToString // 투스트링 메서드
@NoArgsConstructor // 디폴트 생성자 자동완성
@Getter
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId() ,
                comment.getNickname(),
                comment.getBody()
        );
    }
}
