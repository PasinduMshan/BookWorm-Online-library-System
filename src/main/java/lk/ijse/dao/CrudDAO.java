package lk.ijse.dao;

import java.sql.SQLException;

public interface CrudDAO<T> {
    String generateId() throws SQLException;
    String splitId(String currentId);
    boolean save(T entity) throws SQLException;
}
