package com.example.firstproject.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// entity 언노테이션을 반드시 붙여준다.
// 디비가 해당객체를 인식가능하다.
@Entity
public class Article {
    @Id
    @GeneratedValue // 1,2,3, ... 자동 생성 언노테이션
    private Long id; // 엔티티는 반드시 아이디를 넣어줘야한다.

    @Column // 디비에서 이해할수 있도록 각 변수에 컬럼을 추가해야한다.
    private String title;
    @Column
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
