package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        // 이 메소드는 화면단을 가져오기위한 get 메소드
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form) {
        // 브라우저에서 던진 post 가 위 dto 로 받아진다.
        // 이 메소드는 인풋 데이터를 서버측으로 넘겨주기위한 post 메소드다.
        // ...//articles/create&name=""?title=""
        // 웹브라우저에서 받은 데이터를 db(현재는 h2를 사용)에 저장

        // 1. DTO 를 entity 로 변환해야함.
        // 2. 레포지토리에게 엔티디를 디비안에 저장!

        Article article = form.toEntity();

        System.out.println(article.toString());

        Article saved = articleRepository.save(article);

        System.out.println(saved);

        return "";
    }
}
