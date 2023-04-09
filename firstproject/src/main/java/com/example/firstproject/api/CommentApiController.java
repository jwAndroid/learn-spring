package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에게 위임해서 리스트를 가져와서 결과를 내려준다.
        List<CommentDto> dtos = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId , @RequestBody CommentDto dto) {
        // 클라이언트에서 컨트롤러쪽으로 넘어오는건 dto 이다
        // 다시한번 복습하자면 dto 를 디비에 insert 해주기위해 entity(디비가 읽을수 있는 형태)로 변환해야 한다는것이다.

        CommentDto createdDto = commentService.create(articleId , dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto commentDto = commentService.update(id, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        CommentDto dto = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
