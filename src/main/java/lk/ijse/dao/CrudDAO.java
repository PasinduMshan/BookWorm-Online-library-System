package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    String generateId() throws SQLException;

    String splitId(String currentId);

    boolean save(T entity) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(T entity) throws SQLException;

    T search(String Id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;
}
