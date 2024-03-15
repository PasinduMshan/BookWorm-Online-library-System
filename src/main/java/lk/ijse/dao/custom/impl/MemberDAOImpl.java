package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.entity.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public String generateId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select memberId from Member order by memberId desc limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("M0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "M00"+id;
            }else {
                if (length < 3){
                    return "M0"+id;
                }else {
                    return "M"+id;
                }
            }
        }
        return "M001";
    }

    @Override
    public boolean save(Member entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("INSERT INTO Member (memberId, Name, Address, Email, Contact, D_O_B) " +
                    "SELECT :memberId, :Name, :Address, :Email, :Contact, :D_O_B");
            query.setParameter("memberId", entity.getMemberId());
            query.setParameter("Name", entity.getName());
            query.setParameter("Address", entity.getAddress());
            query.setParameter("Email", entity.getEmail());
            query.setParameter("Contact", entity.getContact());
            query.setParameter("D_O_B", entity.getD_O_B());

            int i = query.executeUpdate();

            transaction.commit();

            return i == 1;

        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("DELETE FROM Member WHERE memberId = ?1");
            query.setParameter(1, id);
            int i = query.executeUpdate();

            transaction.commit();

            return i == 1;

        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Member entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE Member SET Name = ?1, Address = ?2, Email = ?3, Contact = ?4, " +
                    "D_O_B = ?5 WHERE memberId = ?6");
            query.setParameter(1, entity.getName());
            query.setParameter(2, entity.getAddress());
            query.setParameter(3, entity.getEmail());
            query.setParameter(4, entity.getContact());
            query.setParameter(5, entity.getD_O_B());
            query.setParameter(6, entity.getMemberId());

            int i = query.executeUpdate();

            transaction.commit();

            return i == 1;

        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Member search(String Id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Member WHERE memberId = ?1");
        query.setParameter(1, Id);
        List<Member> list = query.list();

        Member mem = null;

        for (Member member : list) {
            mem = new Member(member.getMemberId(), member.getName(), member.getAddress(), member.getEmail(),
                    member.getContact(), member.getD_O_B());
        }

        transaction.commit();
        session.close();

        return mem;
    }

    @Override
    public ArrayList<Member> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Member");
        ArrayList<Member> list = (ArrayList<Member>) query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public Member searchMember(String contact) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Member WHERE Contact = ?1");
        query.setParameter(1, contact);
        List<Member> list = query.list();

        Member mem = null;

        for (Member member : list) {
            mem = new Member(member.getMemberId(), member.getName(), member.getAddress(), member.getEmail(),
                    member.getContact(), member.getD_O_B());
        }

        transaction.commit();
        session.close();

        return mem;
    }

    @Override
    public String getMemberCount() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT COUNT(memberId) FROM Member");
        Object cou = query.uniqueResult();

        String count = String.valueOf(cou);

        transaction.commit();
        session.close();

        return count;
    }
}
