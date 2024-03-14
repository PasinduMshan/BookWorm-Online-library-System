package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);
    @Override
    public String generateBookID() throws SQLException {
        return bookDAO.generateId();
    }

    @Override
    public boolean addBook(BookDto bookDto) throws SQLException {
        return bookDAO.save(new Book(
                bookDto.getBookId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCountry(),
                bookDto.getQuantity(),
                bookDto.getAvailableStatus(),
                bookDto.getGenre(),
                branchDAO.search(bookDto.getBranchId())
        ));
    }

    @Override
    public boolean deleteBook(String id) throws SQLException {
        return bookDAO.delete(id);
    }

    @Override
    public BookDto searchBook(String id) throws SQLException {
        Book book = bookDAO.search(id);

        Branch branch = book.getBranch();

        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCountry(),
                book.getQuantity(),
                book.getAvailableStatus(),
                book.getGenre(),
                branch.getBranchId()
        );
    }

    @Override
    public boolean updateBook(BookDto bookDto) throws SQLException {
        return bookDAO.update(new Book(
                bookDto.getBookId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCountry(),
                bookDto.getQuantity(),
                bookDto.getAvailableStatus(),
                bookDto.getGenre(),
                branchDAO.search(bookDto.getBranchId())
        ));
    }

    @Override
    public ArrayList<BookDto> getAllBook() throws SQLException {
        ArrayList<BookDto> bookDtos = new ArrayList<>();

        ArrayList<Book> books = bookDAO.getAll();

        for (Book book : books) {
            Branch branch = book.getBranch();

            bookDtos.add(new BookDto(
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCountry(),
                    book.getQuantity(),
                    book.getAvailableStatus(),
                    book.getGenre(),
                    branch.getBranchId()
            ));
        }
        return bookDtos;
    }

    @Override
    public ArrayList<BranchDto> getAllBranch() throws SQLException {
        ArrayList<BranchDto> branchDtos = new ArrayList<>();

        ArrayList<Branch> branches = branchDAO.getAll();

        for (Branch branch : branches) {
            branchDtos.add(new BranchDto(
                    branch.getBranchId(),
                    branch.getName()
            ));
        }
        return branchDtos;
    }
}
