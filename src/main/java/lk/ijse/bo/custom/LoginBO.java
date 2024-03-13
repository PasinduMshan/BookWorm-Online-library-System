package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean checkCredentials(String userName, String password) throws SQLException;
}
