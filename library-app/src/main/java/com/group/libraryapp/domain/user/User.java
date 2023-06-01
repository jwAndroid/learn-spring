package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    // 테이블에서 동일하기 때문에 매핑시킬 필요가 없다.
    private Integer age;

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다" , name));
        }
        this.name = name;
        this.age = age;
    }

    // 엔티티는 디폴트 생성자가 반드시 필요하다.
    protected User() {}

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
