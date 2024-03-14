package lk.ijse.dto;

public class BranchDto {
    private String BranchId;
    private String Name;

    public BranchDto(String branchId, String name) {
        BranchId = branchId;
        Name = name;
    }

    public BranchDto() {
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
