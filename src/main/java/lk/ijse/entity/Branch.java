package lk.ijse.entity;

import jakarta.persistence.*;

@Entity
public class Branch {
    @Id
    private String BranchId;
    private String Name;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;

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
}
