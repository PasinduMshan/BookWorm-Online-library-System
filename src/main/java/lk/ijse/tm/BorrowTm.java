package lk.ijse.tm;

import java.time.LocalDate;

public class BorrowTm {
    private String memberId;
    private String bookId;
    private String status;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowTm() {
    }

    public BorrowTm(String memberId, String bookId, String status, LocalDate borrowDate, LocalDate returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
