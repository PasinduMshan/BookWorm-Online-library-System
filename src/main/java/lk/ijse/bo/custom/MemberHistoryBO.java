package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MemberDto;
import lk.ijse.dto.QueryDto;

import java.sql.SQLException;
import java.util.List;

public interface MemberHistoryBO extends SuperBO {
    List<QueryDto> getAllTransaction(String id) throws SQLException;

    MemberDto getMemberName(String id) throws SQLException;
}
