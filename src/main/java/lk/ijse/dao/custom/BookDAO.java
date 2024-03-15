package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Book;

import java.sql.SQLException;

public interface BookDAO extends CrudDAO<Book> {
    String getBookCount() throws SQLException;
}
