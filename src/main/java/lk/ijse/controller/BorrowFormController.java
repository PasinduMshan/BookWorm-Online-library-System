package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BorrowBO;
import lk.ijse.bo.custom.MemberBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.MemberDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrow;
import lk.ijse.entity.BorrowDetails;
import lk.ijse.entity.Member;
import lk.ijse.tm.BorrowTm;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BorrowFormController implements Initializable {

    @FXML
    private DatePicker ReturnDate;

    @FXML
    private JFXComboBox<String> cmbBookId;

    @FXML
    private JFXComboBox<String> cmbMemberId;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colStatus;


    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private TableView<BorrowTm> tblBorrow;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    private JFXTextField txtMemberName;

    MemberBO memberBO = (MemberBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEMBER);

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    BorrowBO borrowBO = (BorrowBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROW);

    ObservableList<BorrowTm> borrowTms = FXCollections.observableArrayList();
    private ArrayList<String> arrayList = new ArrayList<>();


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String memberId = cmbMemberId.getValue();
        String bookId = cmbBookId.getValue();
        String status = cmbStatus.getValue();
        LocalDate borrowDate = date.getValue();
        LocalDate returnDate = ReturnDate.getValue();


        try {
            if (bookBO.searchBook(bookId).getAvailableStatus().equals("Unavailable") && status.equals("Borrow")) {
                new Alert(Alert.AlertType.ERROR,"Book Unavailable").show();
                return;
            }
            BorrowTm borrowTm = new BorrowTm(memberId, bookId, status, borrowDate, returnDate);

            if (borrowTms.size()>=3) {
                new Alert(Alert.AlertType.INFORMATION,"Out of limit").show();
                return;
            }

            int i = 0;

            for (BorrowTm borrowTm1 : borrowTms) {
                if (borrowTm1.getBookId().equals(bookId) && borrowTm1.getMemberId().equals(memberId)) {
                    borrowTms.set(i, borrowTm);
                    return;
                }
                i++;
            }

            borrowTms.add(new BorrowTm(
                    memberId,
                    bookId,
                    status,
                    borrowDate,
                    returnDate
            ));

            tblBorrow.setItems(borrowTms);

            generateNext();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateNext() {
        String id = generateOrderDetailId();

        if(arrayList.size() == 0){
            arrayList.add(generateOrderDetailId());
        }else{
            arrayList.add(splitID(arrayList.get(arrayList.size()-1)));
        }
    }

    public String splitID(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("BRD0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "BRD00"+id;
            }else {
                if (length < 3){
                    return "BRD0"+id;
                }else {
                    return "BRD"+id;
                }
            }
        }
        return "BRD001";
    }


    @FXML
    void btnGetBookNameOnAction(ActionEvent event) {
        String id = cmbBookId.getValue();

        try {
            BookDto bookDto = bookBO.searchBook(id);

            txtBookName.setText(bookDto.getTitle());

        } catch (Exception e) {

        }
    }

    @FXML
    void btnGetMemberNameOnAction(ActionEvent event) {
        String id = cmbMemberId.getValue();

        try {
            MemberDto memberDto = memberBO.searchMemberById(id);

            txtMemberName.setText(memberDto.getName());

        } catch (Exception e) {

        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        clearFields();
    }


    @FXML
    void btnBorrowOnAction(ActionEvent event) {

        if (borrowTms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Borrow Table Cart is Empty").show();
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {

            String id = txtBorrowId.getText();
            String memberId = cmbMemberId.getValue();
            String bookId = cmbBookId.getValue();
            String status = cmbStatus.getValue();
            LocalDate borrowDate = this.date.getValue();
            LocalDate returnDate = ReturnDate.getValue();

            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Borrow borrow = new Borrow(id, borrowDate, returnDate, new Member(memberId) );

            session.save(borrow);

            int i = 0;
            for (BorrowTm borrowTm : borrowTms) {
                session.save(new BorrowDetails(
                        arrayList.get(i),
                        borrowTm.getStatus(),
                        borrowTm.getBorrowDate(),
                        borrowTm.getReturnDate(),
                        borrow,
                        new Book(borrowTm.getBookId())
                ));
                i++;
            }

            for (BorrowTm borrowTm : borrowTms) {
                String BookId = borrowTm.getBookId();
                Book book = session.get(Book.class, BookId);
                int quantity = book.getQuantity();

                int newQuantity = (quantity-1);
                book.setQuantity(newQuantity);

                if(newQuantity == 0 ){
                    book.setAvailableStatus("Unavailable");
                }
                session.update(book);
            }

            transaction.commit();

            new Alert(Alert.AlertType.CONFIRMATION, "Borrowed Successfully").show();

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        borrowTms.clear();
        clearFields();
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        if (borrowTms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Borrow Table Cart is Empty").show();
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {
            String id = txtBorrowId.getText();
            String memberId = cmbMemberId.getValue();
            String bookId = cmbBookId.getValue();
            String status = cmbStatus.getValue();
            LocalDate borrowDate = this.date.getValue();
            LocalDate returnDate = ReturnDate.getValue();

            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Borrow borrow = new Borrow(id, borrowDate, returnDate, new Member(memberId) );

            session.save(borrow);

            int i = 0;
            for (BorrowTm borrowTm : borrowTms) {
                session.save(new BorrowDetails(
                        arrayList.get(i),
                        borrowTm.getStatus(),
                        borrowTm.getBorrowDate(),
                        borrowTm.getReturnDate(),
                        borrow,
                        new Book(borrowTm.getBookId())
                ));
                i++;
            }

            for (BorrowTm borrowTm : borrowTms) {
                String BookId = borrowTm.getBookId();
                Book book = session.get(Book.class, BookId);
                int quantity = book.getQuantity();

                int newQuantity = (quantity+1);
                book.setQuantity(newQuantity);

                if(newQuantity > 0 ){
                    book.setAvailableStatus("Available");
                }
                session.update(book);
            }

            transaction.commit();

            new Alert(Alert.AlertType.CONFIRMATION, "Borrowed Successfully").show();

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        borrowTms.clear();
        clearFields();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCurrentDate();
        loadAllMemberId();
        loadAllBookId();
        setStatus();
        setCellValueFactory();
        generateBorrowId();
        generateOrderDetailId();
    }

    private String generateOrderDetailId() {
        try {
            return borrowBO.generateBorrowDetailId();
        } catch (Exception e) {

        }
        return null;
    }

    private void generateBorrowId() {

        try {
            String id = borrowBO.generateBorrowId();
            txtBorrowId.setText(id);
        } catch (Exception e) {

        }

    }

    private void setCellValueFactory() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private void setStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Borrow");
        obList.add("Return");
        cmbStatus.setItems(obList);
    }

    private void loadAllBookId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<MemberDto> memberDtos = memberBO.getAllMember();

            for (MemberDto memberDto : memberDtos) {
                obList.add(memberDto.getMemberId());
            }
            cmbMemberId.setItems(obList);
        } catch (Exception e) {

        }
    }

    private void loadAllMemberId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<BookDto> bookDtos = bookBO.getAllBook();

            for (BookDto bookDto : bookDtos) {
                obList.add(bookDto.getBookId());
            }
            cmbBookId.setItems(obList);
        } catch (Exception e) {

        }
    }

    private void setCurrentDate() {
        date.setValue(LocalDate.now());
    }

    private void clearFields() {
        txtBorrowId.clear();
        cmbBookId.setValue(null);
        txtBookName.clear();
        date.setValue(null);
        cmbStatus.setValue(null);
        cmbMemberId.setValue(null);
        txtMemberName.clear();
        ReturnDate.setValue(null);
        generateBorrowId();
        setCurrentDate();
    }
}
