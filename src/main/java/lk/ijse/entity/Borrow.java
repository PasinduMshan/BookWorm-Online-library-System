package lk.ijse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Borrow")
public class Borrow {
    @Id
    private String borrowId;
    private LocalDate BorrowDate;
    private LocalDate DueDate;

    @ManyToOne
    private Member members;

    @OneToMany(mappedBy = "borrow", cascade = CascadeType.ALL)
    private List<BorrowDetails> borrowDetails;

    public Borrow() {
    }

    public Borrow(String borrowId) {
        this.borrowId = borrowId;
    }

    public Borrow(String borrowId, LocalDate borrowDate, LocalDate dueDate, Member member) {
        this.borrowId = borrowId;
        BorrowDate = borrowDate;
        DueDate = dueDate;
        this.members = member;
    }

    public Borrow(String borrowId, LocalDate borrowDate, LocalDate dueDate, Member member, List<BorrowDetails> borrowDetails) {
        this.borrowId = borrowId;
        BorrowDate = borrowDate;
        DueDate = dueDate;
        this.members = member;
        this.borrowDetails = borrowDetails;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public LocalDate getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        BorrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    public Member getMembers() {
        return members;
    }

    public void setMembers(Member member) {
        this.members = member;
    }

    public List<BorrowDetails> getBorrowDetails() {
        return borrowDetails;
    }

    public void setBorrowDetails(List<BorrowDetails> borrowDetails) {
        this.borrowDetails = borrowDetails;
    }
}
