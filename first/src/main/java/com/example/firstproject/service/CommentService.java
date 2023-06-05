package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회: 댓글 목록을 디비에서 조회
        // 엔티티로 변환해야한다.
        // 반환
        return commentRepository
                .findByArticleId(articleId) // 조회: 댓글 목록을 디비에서 조회
                .stream() // stream 변환
                .map(CommentDto::createCommentDto) // 엔티티로 변환해야한다.
                .collect(Collectors.toList()); // 스트림으로 빠져나오니 컬렉션으로 변환
    }

    @Transactional // 디비에 변경이 이루어날수 있기때문에 무조건 트랜잭션 처리!
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외처리
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!"));
        // 만약 예외가 발생된다면 아래에 있는 구문이 실행되지 않는다. 그리고 트랜잭션을 실행한다.

        // 디비에 저장하기위한 클라이언트에서 넘어온 데이터를 엔티티로 변환.
        Comment entity = Comment.createComment(dto, article);

        // 엔티티로 변환한걸 디비에 저장.
        Comment created = commentRepository.save(entity);

        // 다시 디티오로 변경하여서 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 수정 대상 댓글 조회 및 예외처리
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("fal"));

        // 조회가 잘되면 수정하려는 dto 를 가지고 댓글을 수정
        target.patch(dto);

        // 디비에 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 디티오로 변환 후 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외처리
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("fal delete"));

        // 댓글 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 디티오로 반환
        return CommentDto.createCommentDto(target);
    }
}

