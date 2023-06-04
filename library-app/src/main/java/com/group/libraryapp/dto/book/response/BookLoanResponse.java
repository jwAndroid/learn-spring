package com.group.libraryapp.dto.book.response;

public class BookLoanResponse {
    private final Long id;
    private final long userId;
    private final String bookName;
    private final boolean isReturn;

    public BookLoanResponse(Long id, long userId, String bookName, boolean isReturn) {
        this.id = id;
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = isReturn;
    }

    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
