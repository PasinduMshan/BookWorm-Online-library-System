package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    String generateNextUserId() throws SQLException;
    boolean addAdmin(AdminDto dto) throws SQLException;
}
