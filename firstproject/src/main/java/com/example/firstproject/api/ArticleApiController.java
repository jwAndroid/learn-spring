package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired // DI 생성 객체를 가져와서 연결
    ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);

        return created != null
                ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) ;
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
       Article updated = articleService.update(id , dto);

        return updated != null
                ? ResponseEntity.status(HttpStatus.OK).body(updated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return deleted != null
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    /// 하단 부터는 서비스 계층이 없는 코드 ///
//    @Autowired
//    // 스프링 부트에서 땡겨와야 하기 떄문에
//    // 이게 종속성 주입이다. 외부에서 가져올때 사용
//    private ArticleRepository articleRepository;
//
//    // GET 다건
//    @GetMapping("/api/articles")
//    public List<Article> articles() {
//        return articleRepository.findAll();
//    }
//
//    // GET 단건
//    @GetMapping("/api/articles/{id}")
//    public Article article(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        // 클라이언트가 DTO 로 데이터를 보낸건 파라미터에서 받아준다.
//        // 여기서 중요한건 클라이언트가 던진 json 데이터는 @RequestBody 로 받아줘야한다.
//        // DTO -> entity -> repository
//        Article entity = dto.toEntity();
//
//        return articleRepository.save(entity);
//    }
//
//    // PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id , @RequestBody ArticleForm dto) {
//        // 수정용 엔티티 생성
//       Article entity = dto.toEntity();
//       log.info(entity.toString());
//
//        // 바꿀 대상엔티티를 디비에서 조회해야한다.
//       Article target = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리 대상이 없거나, 아이디가 다른경우
//        if (target == null || id != entity.getId()) {
//            // 400, 떨궈주기
//            // 여기서 반환값이 그냥 article 이 아니라 ResponseEntity<Article> 가 되어야한다.
//            log.info("잘못된 요청!");
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 클라이언트에서 필드를 뺄때경우를 대비해준다.
//        // 이런경우 예를들어 타이틀을 빼면, 기존 타이틀의 값은 유지채한채 바꿔줄수 있다.
//        target.patch(entity);
//
//        // 업데이트 및 정상 응답 떨궈주기(200)
//        Article updated = articleRepository.save(target);
//
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
    // .build(); 는 그냥 아무것도 없이 리턴시켜줄때
//    }
}
