package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Object[]> getAllLateReturns() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT m.memberId, m.Name, b.Title, br.BorrowDate, br.DueDate FROM Member " +
                "m JOIN Borrow br ON m.memberId = br.members.memberId JOIN BorrowDetails bd ON br.borrowId = bd.borrow.borrowId " +
                "JOIN Book b ON bd.book.BookId = b.BookId WHERE br.DueDate < CURRENT_DATE() AND bd.status = ?1");
        query.setParameter(1, "Borrow");
        List<Object[]> list = query.list();


        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getAllMemberHistory(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT m.memberId, m.Name, b.Title, bd.borrowDate, bd.returnDate, bd.status " +
                "FROM Member m JOIN Borrow br ON m.memberId = br.members.memberId JOIN BorrowDetails bd " +
                "ON br.borrowId = bd.borrow.borrowId JOIN Book b ON bd.book.BookId = b.BookId WHERE m.memberId = ?1");
        query.setParameter(1, id);
        List<Object[]> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
