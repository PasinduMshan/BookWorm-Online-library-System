package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface BorrowBO extends SuperBO {
    String generateBorrowId() throws SQLException;

    String generateBorrowDetailId() throws SQLException;
}
