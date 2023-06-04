package com.group.libraryapp.domain.user.loanhistory;
import javax.persistence.*;

@Entity
@Table(name = "user_loan_history")
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "is_return", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isReturn;

    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    protected UserLoanHistory() {}

    public void doReturn() {
        this.isReturn = true;
    }
}
