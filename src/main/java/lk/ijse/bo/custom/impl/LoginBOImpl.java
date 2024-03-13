package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public boolean checkCredentials(String userName, String password) throws SQLException {
        return adminDAO.checkCredentialsByPassword(userName, password);
    }
}
