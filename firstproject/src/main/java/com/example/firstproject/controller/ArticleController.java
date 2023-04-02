package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm() {
        // 이 메소드는 화면단을 가져오기위한 get 메소드고
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form) {
        // 브라우저에서 던진 post 가 위 dto 로 받아진다.
        // 이 메소드는 인풋 데이터를 서버측으로 넘겨주기위한 post 메소드다.

        System.out.println(form.toString());
        return "";
    }
}
