package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    // Comment 엔티티 생성직후 테이블이 생성된다.

    // 컬럼 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 아티클에 연관된다.
    @JoinColumn(name = "article_id") // 어떤 게시글인지 알아야한다. 이 컬럼의 아이디를 article_id 라 한다.
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외처리
        if (dto.getId() != null) throw new IllegalArgumentException("실패");
        if(!Objects.equals(dto.getArticleId(), article.getId())) throw new IllegalArgumentException("실패");

        // 엔티티 생성 및 반환
        return new Comment(
                null,
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId()) throw new IllegalArgumentException("fal??? ");

        // 객체를 갱신
        if(dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }

        if(dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
