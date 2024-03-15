package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.QueryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashBoardBO extends SuperBO {
    ArrayList<QueryDto> loadAllLateReturn() throws SQLException;
}
