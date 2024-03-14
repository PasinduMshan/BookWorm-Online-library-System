package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    @Override
    public String generateId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT BranchId from Branch order by BranchId desc limit 1");
        String ad_Id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(ad_Id);
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("B0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "B00"+id;
            }else {
                if (length < 3){
                    return "B0"+id;
                }else {
                    return "B"+id;
                }
            }
        }
        return "B001";
    }

    @Override
    public boolean save(Branch entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("INSERT INTO Branch (BranchId, Name, admin) SELECT :BranchId, :Name, :admin");
        query.setParameter("BranchId", entity.getBranchId());
        query.setParameter("Name", entity.getName());
        query.setParameter("admin", entity.getAdmin());

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Branch WHERE BranchId = ?1");
        query.setParameter(1, id);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public boolean update(Branch entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE Branch SET Name = ?1, admin = ?2 WHERE BranchId = ?3");
        query.setParameter(1, entity.getName());
        query.setParameter(2, entity.getAdmin());
        query.setParameter(3, entity.getBranchId());

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public Branch search(String Id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Branch WHERE BranchId = ?1");
        query.setParameter(1, Id);
        List<Branch> list = query.list();

        Branch branch = null;

        for (Branch branch1 : list) {
            branch = new Branch(branch1.getBranchId(), branch1.getName() , branch1.getAdmin());
        }

        transaction.commit();
        session.close();

        return branch;
    }

    @Override
    public ArrayList<Branch> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Branch ");
        ArrayList<Branch> list = (ArrayList<Branch>) query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
