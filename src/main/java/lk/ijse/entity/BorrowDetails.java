package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "BorrowDetails")
public class BorrowDetails {
    @Id
    private String borrowDetailId;
    private String status;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    @ManyToOne
    private Borrow borrow;

    @ManyToOne
    private Book book;

    public BorrowDetails() {
    }

    public BorrowDetails(String bookDetailId, String status, LocalDate borrowDate, LocalDate returnDate, Borrow borrow, Book book) {
        this.borrowDetailId = bookDetailId;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrow = borrow;
        this.book = book;
    }

    public String getBorrowDetailId() {
        return borrowDetailId;
    }

    public void setBorrowDetailId(String borrowDetailId) {
        this.borrowDetailId = borrowDetailId;
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

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
