package com.example.firstproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

// entity 언노테이션을 반드시 붙여준다.
// 디비가 해당객체를 인식가능하다.
@Entity // 해당 클래스로 테이블이 생성된다 해당 언노테이션으로
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 디비가 아이디를 알아서 자동생성해줌.
    // 1,2,3, ... 자동 생성 언노테이션
    private Long id; // 엔티티는 반드시 아이디를 넣어줘야한다.

    @Column // 디비에서 이해할수 있도록 각 변수에 컬럼을 추가해야한다.
    private String title;
    @Column
    private String content;

    // 이렇게 안쓴다 롬복을 쓴다.
//    public Long getId() {
//        return id;
//    }

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }

        if(article.content != null) {
            this.content = article.content;
        }
    }
}
