package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스트된다.
class ArticleServiceTest {

    // 아티클 서비스를 땡겨와야한다.
    @Autowired
    ArticleService articleService;

    @Test // 테스트를 위한 어노테이션
    void index() {
        // 예상
        Article a =  new Article(1L, "a" ,"ac");
        Article b =  new Article(2L, "b" ,"ab");
        Article c =  new Article(3L, "c" ,"ac");

        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        // 생성자에 Arrays.asList(a, b, c) 로 새롭게 만들수가 있다.

        // 실제
        List<Article> articles = articleService.index();

        // 비교

        assertEquals(expected.toString() , articles.toString());

    }

    @Test
    void show() {
        // 예상
        Long id = 1L;
        Article ex =  new Article(id, "c" ,"ac");

        // 실제
        Article article = articleService.show(id);

        // 비교

        assertEquals(ex.toString() , article.toString());
        // null 은 toString() 메소드를 호출할수 없다.
        // null 을 리턴할수도 있는건 예외적으로 처리해줘야한다.
    }

    @Test
    @Transactional // 생성, 변경, 삭제는 롤백할수 있게 처리해줘야한다.
    void create() {
        ArticleForm dto = new ArticleForm(null , "asd" , "Asd");
        Article ex = new Article(4L , "asd" , "Asd");

        Article article = articleService.create(dto);

        assertEquals(dto.toEntity() , ex.toString());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void createArticles() {
    }
}
