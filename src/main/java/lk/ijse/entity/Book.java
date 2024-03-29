package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    private String BookId;
    private String Title;
    private String Author;
    private String Country;
    private int Quantity;
    private String AvailableStatus;
    private String Genre;

    @ManyToOne
    @JoinColumn(name = "BranchId")
    private Branch branch;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowDetails> borrowDetails;

    public Book() {
    }

    public Book(String bookId) {
        BookId = bookId;
    }

    public Book(String bookId, String title, String author, String country, int quantity, String availableStatus, String genre, Branch branch) {
        BookId = bookId;
        Title = title;
        Author = author;
        Country = country;
        Quantity = quantity;
        AvailableStatus = availableStatus;
        Genre = genre;
        this.branch = branch;
    }

    public Book(String bookId, String title, String author, String country, int quantity, String availableStatus, String genre, Branch branch, List<BorrowDetails> borrowDetails) {
        BookId = bookId;
        Title = title;
        Author = author;
        Country = country;
        Quantity = quantity;
        AvailableStatus = availableStatus;
        Genre = genre;
        this.branch = branch;
        this.borrowDetails = borrowDetails;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getAvailableStatus() {
        return AvailableStatus;
    }

    public void setAvailableStatus(String availableStatus) {
        AvailableStatus = availableStatus;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


}
