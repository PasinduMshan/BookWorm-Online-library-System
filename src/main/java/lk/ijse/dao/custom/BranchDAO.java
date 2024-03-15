package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Branch;

import java.sql.SQLException;

public interface BranchDAO extends CrudDAO<Branch> {
    String getBranchCount() throws SQLException;
}
