package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BorrowDAO;
import lk.ijse.entity.Borrow;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowDAOImpl implements BorrowDAO {
    @Override
    public String generateId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT borrowId FROM Borrow ORDER BY borrowId DESC LIMIT 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("BOR0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "BOR00"+id;
            }else {
                if (length < 3){
                    return "BOR0"+id;
                }else {
                    return "BRO"+id;
                }
            }
        }
        return "BOR001";
    }

    @Override
    public boolean save(Borrow entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Borrow entity) throws SQLException {
        return false;
    }

    @Override
    public Borrow search(String Id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Borrow> getAll() throws SQLException {
        return null;
    }
}
