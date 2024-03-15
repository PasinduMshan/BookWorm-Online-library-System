package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.QueryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashBoardBO extends SuperBO {
    ArrayList<QueryDto> loadAllLateReturn() throws SQLException;

    String getAdminName(String name, String password) throws SQLException;

    String getBookCount() throws SQLException;

    String getMemberCount() throws SQLException;
}
