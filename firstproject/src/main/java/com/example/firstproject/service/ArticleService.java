package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
// 서비스는 이렇게 선언해준다.
// 서비스 객체를 스프링부트에 생성한다.

public class ArticleService {
    // 이 서비스가 레포지토리와 협업할수 있게끔 추가해준다.

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
       return articleRepository.findAll();
    }

    public Article show(Long id) {
       return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        if (article.getId() != null) {
            // 요청에 아이디가 실려들어왔을때 null 을 리턴시켜준다.
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()) {
            return null;
        }

        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
       Article target = articleRepository.findById(id).orElse(null);

       if(target == null) {
           return null;
       }

       articleRepository.delete(target);
       return target;
    }

    // 해당 메소드를 트랙잭션으로 묶는다.
    // 만약에 실패하면 메소드가 수행되기 이전상태로 되돌려 버린다.
    // 그래서 그전에 들어갔던 디비가 초기화되는것.
    // 이부분 상당히 중요함.
    // 디비에 잘못된 데이터가 들어가면 안되니 처음상태로 되돌려주는것.
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 엔티티 묶음으로 변환!
        // 리스트 묶음을 스트림화 시켜서 맵을 돌린다.
      List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList()); // 다시 리스트로 만들어준다.

        // 엔티티 묶음을 디비로 저장
      articleList.stream().forEach(article -> articleRepository.save(article));

      articleRepository.findById(-1L).orElseThrow(
              () -> new IllegalArgumentException("결제 실패!")
      );

        // 저장된 상태에서 강제로 예외 발생!

        // 결과값 반환

        return articleList;
    }
}
