package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public String generateId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select adminId from Admin order by adminId desc limit 1");
        String ad_Id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(ad_Id);
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("A0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "A00"+id;
            }else {
                if (length < 3){
                    return "A0"+id;
                }else {
                    return "A"+id;
                }
            }
        }
        return "A001";
    }

    @Override
    public boolean save(Admin entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("INSERT INTO Admin (adminId, Name, Email, userName, password) SELECT :adminId, :Name, :Email, :userName, :password");
        query.setParameter("adminId", entity.getAdminId());
        query.setParameter("Name" , entity.getName());
        query.setParameter("Email" , entity.getEmail());
        query.setParameter("userName" , entity.getUserName());
        query.setParameter("password" , entity.getPassword());

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Admin WHERE adminId = ?1");
        query.setParameter(1, id);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public boolean update(Admin entity) throws SQLException {
        return false;
    }

    @Override
    public Admin search(String Id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Admin WHERE adminId = ?1");
        query.setParameter(1, Id);
        List<Admin> list = query.list();

        Admin admin = null;

        for (Admin admin1 : list) {
            admin = new Admin(admin1.getAdminId(), admin1.getName(), admin1.getEmail(), admin1.getUserName(), admin1.getPassword());
        }

        transaction.commit();
        session.close();

        return admin;
    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Admin");
        ArrayList<Admin> list = (ArrayList<Admin>) query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public boolean checkCredentialsByPassword(String name, String password) throws SQLException{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Admin WHERE userName = ?1 AND password = ?2");
        query.setParameter(1, name);
        query.setParameter(2, password);

        List<Admin> adminList = query.list();

        String userName = null;
        String Password = null;

        for (Admin admin : adminList) {
            userName = admin.getUserName();
            Password = admin.getPassword();
        }

        transaction.commit();
        session.close();

        if (name.equals(userName) && password.equals(Password)) {
            return true;
        }
        return false;
    }

    @Override
    public Admin getID(String name, String password) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Admin WHERE userName = ?1 AND password = ?2");
        query.setParameter(1, name);
        query.setParameter(2, password);
        List<Admin> list = query.list();

        Admin admin = null;

        for (Admin admin1 : list) {
            admin = new Admin(admin1.getAdminId(), admin1.getName(), admin1.getEmail(), admin1.getUserName(), admin1.getPassword());
        }

        transaction.commit();
        session.close();

        return admin;
    }
}
