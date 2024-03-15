package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public String generateId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT BookId FROM Book ORDER BY BookId DESC limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "P00"+id;
            }else {
                if (length < 3){
                    return "P0"+id;
                }else {
                    return "P"+id;
                }
            }
        }
        return "P001";
    }

    @Override
    public boolean save(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("INSERT INTO Book (BookId, Title, Author, Country, Quantity, AvailableStatus, " +
                    "Genre, branch) SELECT :BookId, :Title, :Author, :Country, :Quantity, :AvailableStatus, :Genre, :branch");
            query.setParameter("BookId", entity.getBookId());
            query.setParameter("Title", entity.getTitle());
            query.setParameter("Author", entity.getAuthor());
            query.setParameter("Country", entity.getCountry());
            query.setParameter("Quantity", entity.getQuantity());
            query.setParameter("AvailableStatus", entity.getAvailableStatus());
            query.setParameter("Genre", entity.getGenre());
            query.setParameter("branch", entity.getBranch());

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
            Query query = session.createQuery("DELETE FROM Book WHERE BookId = ?1");
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
    public boolean update(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE Book SET Title = ?1, Author = ?2, Country = ?3, Quantity = ?4, " +
                    "AvailableStatus = ?5, Genre = ?6, branch = ?7 WHERE BookId = ?8");

            query.setParameter(1, entity.getTitle());
            query.setParameter(2, entity.getAuthor());
            query.setParameter(3, entity.getCountry());
            query.setParameter(4, entity.getQuantity());
            query.setParameter(5, entity.getAvailableStatus());
            query.setParameter(6, entity.getGenre());
            query.setParameter(7, entity.getBranch());
            query.setParameter(8, entity.getBookId());

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
    public Book search(String Id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Book WHERE BookId = ?1");
        query.setParameter(1, Id);
        List<Book> list = query.list();

        Book book = null;

        for (Book book1 : list) {
            book = new Book(
                    book1.getBookId(),
                    book1.getTitle(),
                    book1.getAuthor(),
                    book1.getCountry(),
                    book1.getQuantity(),
                    book1.getAvailableStatus(),
                    book1.getGenre(),
                    book1.getBranch()
            );
        }

        transaction.commit();
        session.close();

        return book;
    }

    @Override
    public ArrayList<Book> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Book ");
        ArrayList<Book> list = (ArrayList<Book>) query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public String getBookCount() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT COUNT(BookId) FROM Book");
        Object o = query.uniqueResult();

        System.out.println(o);

        String count = String.valueOf(o);

        transaction.commit();
        session.close();

        return count;
    }
}
