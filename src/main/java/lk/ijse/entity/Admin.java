package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Admin {
    @Id
    private String adminId;
    private String Name;
    private String Email;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Branch> branchList;

    public Admin() {
    }

    public Admin(String adminId, String name, String email, String userName, String password) {
        this.adminId = adminId;
        Name = name;
        Email = email;
        this.userName = userName;
        this.password = password;
    }

    public Admin(String adminId, String name, String email, String userName, String password, List<Branch> branchList) {
        this.adminId = adminId;
        Name = name;
        Email = email;
        this.userName = userName;
        this.password = password;
        this.branchList = branchList;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
