package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    private final String title;
    private final String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(null , title , content);
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
