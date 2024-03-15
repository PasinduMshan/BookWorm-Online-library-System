package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BorrowBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BorrowDAO;
import lk.ijse.dao.custom.BorrowDetailDAO;

import java.sql.SQLException;

public class BorrowBOImpl implements BorrowBO {
    BorrowDAO borrowDAO = (BorrowDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BORROW);
    BorrowDetailDAO borrowDetailDAO = (BorrowDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BORROWDETAIL);
    @Override
    public String generateBorrowId() throws SQLException {
        return borrowDAO.generateId();
    }

    @Override
    public String generateBorrowDetailId() throws SQLException {
        return borrowDetailDAO.generateId();
    }
}
