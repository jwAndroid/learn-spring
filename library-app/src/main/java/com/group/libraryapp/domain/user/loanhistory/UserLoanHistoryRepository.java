package com.group.libraryapp.domain.user.loanhistory;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.dto.book.response.BookLoanResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory , Long> {
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

    Optional<UserLoanHistory> findByUserIdAndBookName(long userId,  String bookName);

    Optional<List<BookLoanResponse>> findAllByUserId(long userId);
}
