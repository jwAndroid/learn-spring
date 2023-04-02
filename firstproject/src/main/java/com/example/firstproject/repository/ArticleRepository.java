package com.example.firstproject.repository;


// 레포지토리는 인터페이스이다.

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // 제네릭에 첫번째 인자는 관리대상 엔티티를 적어주고, 두번째 인자에는 해당 엔티티의 최상위 값 id 를 적어준다.
    // 이렇게 하면 생성하고 읽고 수정하고 삭제가 가능.
}
