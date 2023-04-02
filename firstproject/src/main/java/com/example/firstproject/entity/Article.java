package com.example.firstproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// entity 언노테이션을 반드시 붙여준다.
// 디비가 해당객체를 인식가능하다.
@Entity
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue // 1,2,3, ... 자동 생성 언노테이션
    private Long id; // 엔티티는 반드시 아이디를 넣어줘야한다.

    @Column // 디비에서 이해할수 있도록 각 변수에 컬럼을 추가해야한다.
    private String title;
    @Column
    private String content;

    // 이렇게 안쓴다 롬복을 쓴다.
//    public Long getId() {
//        return id;
//    }
}
