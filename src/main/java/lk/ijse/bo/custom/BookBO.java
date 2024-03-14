package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO {
    String generateBookID() throws SQLException;

    boolean addBook(BookDto bookDto) throws SQLException;

    boolean deleteBook(String id) throws SQLException;

    BookDto searchBook(String id) throws SQLException;

    boolean updateBook(BookDto bookDto) throws SQLException;

    ArrayList<BookDto> getAllBook() throws SQLException;

    ArrayList<BranchDto> getAllBranch() throws SQLException;
}
