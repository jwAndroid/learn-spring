package com.group.libraryapp.repository.book;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class BookMySqlRepository implements BookRepository {

    @Override
    public void saveBook() {
        System.out.println("mysql 레포지토리 사용하고 있는중 ㅋㅋ");
    }
}
