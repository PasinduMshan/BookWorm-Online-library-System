package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Branch {
    @Id
    private String BranchId;
    private String Name;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Branch() {
    }

    public Branch(String branchId, String name, Admin admin) {
        BranchId = branchId;
        Name = name;
        this.admin = admin;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
