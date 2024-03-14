package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDto;

import java.sql.SQLException;
import java.util.ArrayList;


public interface BranchBO extends SuperBO {
    String generateBranchId() throws SQLException;

    boolean saveBranch(BranchDto dto) throws SQLException;

    boolean deleteBranch(String id) throws SQLException;

    BranchDto searchBranch(String id) throws SQLException;

    boolean updateBranch(BranchDto dto) throws SQLException;

    ArrayList<BranchDto> getAllBranches() throws SQLException;
}
