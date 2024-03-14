package lk.ijse.dao;

import lk.ijse.dao.custom.impl.AdminDAOImpl;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dao.custom.impl.MemberDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADMIN, MEMBER, BRANCH, BOOK
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case ADMIN:
                return new AdminDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            default:
                return null;
        }
    }
}
