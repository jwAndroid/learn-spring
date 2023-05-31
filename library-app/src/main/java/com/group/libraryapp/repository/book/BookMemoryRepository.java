package com.group.libraryapp.repository.book;
import org.springframework.stereotype.Repository;

@Repository
public class BookMemoryRepository implements BookRepository {
    public void saveBook() {
        System.out.println("메모리 레포지토리 사용하고 있는중 ㅋㅋ");
    }
}
