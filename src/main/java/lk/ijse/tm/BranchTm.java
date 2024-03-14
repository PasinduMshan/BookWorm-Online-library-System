package lk.ijse.tm;

public class BranchTm {
    private String BranchId;
    private String Name;

    public BranchTm(String branchId, String name) {
        BranchId = branchId;
        Name = name;
    }

    public BranchTm() {
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
}
