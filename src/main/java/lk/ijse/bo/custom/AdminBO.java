package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminBO extends SuperBO {
    String generateId() throws SQLException;

    boolean saveAdmin(AdminDto dto) throws SQLException;

    boolean deleteAdmin(String id) throws SQLException;

    boolean updateAdmin(AdminDto dto) throws SQLException;

    AdminDto searchAdmin(String id) throws SQLException;

    ArrayList<AdminDto> getAllAdmin() throws SQLException;
}
