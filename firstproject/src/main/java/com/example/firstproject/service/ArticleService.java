package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
