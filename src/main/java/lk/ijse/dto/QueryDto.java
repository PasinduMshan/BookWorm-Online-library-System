package lk.ijse.dto;

public class QueryDto {
    private String memberId;
    private String memberName;
    private String bookName;
    private String BorrowDate;
    private String ReturnDate;
    private String Status;

    public QueryDto() {
    }

    public QueryDto(String memberId, String memberName, String bookName, String borrowDate, String returnDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookName = bookName;
        BorrowDate = borrowDate;
        ReturnDate = returnDate;
    }

    public QueryDto(String memberId, String memberName, String bookName, String borrowDate, String returnDate, String status) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookName = bookName;
        BorrowDate = borrowDate;
        ReturnDate = returnDate;
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        BorrowDate = borrowDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }
}
