package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BorrowDetailDAO;
import lk.ijse.entity.BorrowDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowDetailDAOImpl implements BorrowDetailDAO {
    @Override
    public String generateId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT borrowDetailId FROM BorrowDetails ORDER BY borrowDetailId DESC LIMIT 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        if (id != null) {
            return splitId(id);
        } else {
            return splitId(null);
        }
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("BRD0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "BRD00"+id;
            }else {
                if (length < 3){
                    return "BRD0"+id;
                }else {
                    return "BRD"+id;
                }
            }
        }
        return "BRD001";
    }

    @Override
    public boolean save(BorrowDetails entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BorrowDetails entity) throws SQLException {
        return false;
    }

    @Override
    public BorrowDetails search(String Id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<BorrowDetails> getAll() throws SQLException {
        return null;
    }
}
