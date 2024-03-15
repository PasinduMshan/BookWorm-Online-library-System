package lk.ijse.tm;

public class MemberHistoryTm {
    private String memberId;
    private String bookName;
    private String BorrowDate;
    private String ReturnDate;
    private String Status;

    public MemberHistoryTm() {
    }

    public MemberHistoryTm(String memberId, String bookName, String borrowDate, String returnDate, String status) {
        this.memberId = memberId;
        this.bookName = bookName;
        BorrowDate = borrowDate;
        ReturnDate = returnDate;
        Status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
