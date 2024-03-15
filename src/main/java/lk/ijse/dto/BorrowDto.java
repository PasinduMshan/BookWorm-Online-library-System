package lk.ijse.dto;

import java.time.LocalDate;

public class BorrowDto {
    private String BorrowId;
    private String BookId;
    private String MemberId;
    private String Status;
    private LocalDate date;
    private LocalDate returnDate;
    private String BookName;
    private String MemberName;

    public BorrowDto() {
    }

    public BorrowDto(String borrowId, String bookId, String memberId, String status, LocalDate date, LocalDate returnDate, String bookName, String memberName) {
        BorrowId = borrowId;
        BookId = bookId;
        MemberId = memberId;
        Status = status;
        this.date = date;
        this.returnDate = returnDate;
        BookName = bookName;
        MemberName = memberName;
    }

    public BorrowDto(String borrowId, String bookId, String memberId, String status, LocalDate date, LocalDate returnDate) {
        BorrowId = borrowId;
        BookId = bookId;
        MemberId = memberId;
        Status = status;
        this.date = date;
        this.returnDate = returnDate;
    }

    public BorrowDto(String borrowId, String bookId, String memberId, LocalDate date, LocalDate returnDate) {
        BorrowId = borrowId;
        BookId = bookId;
        MemberId = memberId;
        this.date = date;
        this.returnDate = returnDate;
    }

    public String getBorrowId() {
        return BorrowId;
    }

    public void setBorrowId(String borrowId) {
        BorrowId = borrowId;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }
}
