package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin> {
    boolean checkCredentialsByPassword(String name, String password) throws SQLException;

    Admin getID(String name, String password) throws SQLException;

    String getAdminName(String name, String password) throws SQLException;
}
