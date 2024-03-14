package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.tm.BookTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BookFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbAvailability;

    @FXML
    private JFXComboBox<String> cmbBranchId;

    @FXML
    private JFXComboBox<String> cmbGenre;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colGenres;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtBookID;

    @FXML
    private JFXTextField txtCountry;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtTitle;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtBookID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String country = txtCountry.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        String status = cmbAvailability.getValue();
        String genre = cmbGenre.getValue();
        String branchId = cmbBranchId.getValue();

        boolean isValidate = validateDetails(title, author, country, qty);

        if (isValidate) {
            BookDto bookDto = new BookDto(id, title, author, country, qty, status, genre, branchId);

            try {
                boolean isSaved = bookBO.addBook(bookDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION,"Book Save Success!!!").showAndWait();
                    clearField();
                    loadAllBooks();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Save Failed!!!").showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateDetails(String title, String author, String country, Integer qty) {
        boolean matches = Pattern.matches("[A-Za-z0-9.\\s]+", title);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR,"Invalid Title!!").show();
            return false;
        }

        boolean matches1 = Pattern.matches("[A-Za-z.\\s]+", author);
        if (!matches1) {
            new Alert(Alert.AlertType.ERROR,"Invalid Author!!").show();
            return false;
        }

        boolean matches2 = Pattern.matches("[A-Za-z.\\s]+", country);
        if (!matches2) {
            new Alert(Alert.AlertType.ERROR,"Invalid Country!!").show();
            return false;
        }

        boolean matches3 = Pattern.matches("[0-9]+", String.valueOf(qty));
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR,"Invalid Country!!").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtBookID.getText();

        try {
            boolean isDeleted = bookBO.deleteBook(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Delete Success!!!").showAndWait();
                clearField();
                loadAllBooks();
            } else  {
                new Alert(Alert.AlertType.ERROR,"Delete Failed!!!").showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtBookID.getText();

        try {
            BookDto bookDto = bookBO.searchBook(id);

            if (bookDto != null) {
                txtBookID.setText(bookDto.getBookId());
                txtTitle.setText(bookDto.getTitle());
                txtAuthor.setText(bookDto.getAuthor());
                txtCountry.setText(bookDto.getCountry());
                txtQty.setText(String.valueOf(bookDto.getQuantity()));
                cmbAvailability.setValue(bookDto.getAvailableStatus());
                cmbGenre.setValue(bookDto.getGenre());
                cmbBranchId.setValue(bookDto.getBranchId());
            } else {
                new Alert(Alert.AlertType.ERROR,"Book Not Found!!!").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtBookID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String country = txtCountry.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        String status = cmbAvailability.getValue();
        String genre = cmbGenre.getValue();
        String branchId = cmbBranchId.getValue();

        boolean isValidate = validateDetails(title, author, country, qty);

        if (isValidate) {
            BookDto bookDto = new BookDto(id, title, author, country, qty, status, genre, branchId);

            try {
                boolean isUpdated = bookBO.updateBook(bookDto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION,"Update Success!!!").showAndWait();
                    clearField();
                    loadAllBooks();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Update Failed!!!").showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        clearField();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateBookId();
        setCellValueFactory();
        loadAllBooks();
        tableListener();
        loadAvailabilityStatus();
        loadAllGenres();
        loadAllBranchId();
    }

    private void loadAllBranchId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<BranchDto> allBranch = bookBO.getAllBranch();

            for (BranchDto branchDto : allBranch) {
                obList.add(branchDto.getBranchId());
            }
            cmbBranchId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllGenres() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        String[] genre = {"Mystery", "Romance", "Science Fiction", "Fantasy", "Historical Fiction", "Thriller/Suspense",
                "Biography/Autobiography", "Memoir", "Health/Fitness", "Poetry"};

        for (int i = 0; i < genre.length; i++) {
            obList.add(genre[i]);
        }

        cmbGenre.setItems(obList);
    }

    private void loadAvailabilityStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Available");
        obList.add("Unavailable");
        cmbAvailability.setItems(obList);
    }

    private void tableListener() {
        tblBook.getSelectionModel().selectedItemProperty().addListener((observableValue, bookTm, t1) -> {

            try {
                BookDto bookDto = bookBO.searchBook(t1.getBookId());

                if (bookDto != null) {
                    txtBookID.setText(bookDto.getBookId());
                    txtTitle.setText(bookDto.getTitle());
                    txtAuthor.setText(bookDto.getAuthor());
                    txtCountry.setText(bookDto.getCountry());
                    txtQty.setText(String.valueOf(bookDto.getQuantity()));
                    cmbAvailability.setValue(bookDto.getAvailableStatus());
                    cmbGenre.setValue(bookDto.getGenre());
                    cmbBranchId.setValue(bookDto.getBranchId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadAllBooks() {
        ObservableList<BookTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<BookDto> allBook = bookBO.getAllBook();

            for (BookDto bookDto : allBook) {
                obList.add(new BookTm(
                        bookDto.getBookId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getGenre(),
                        bookDto.getAvailableStatus(),
                        bookDto.getQuantity()
                ));
            }
            tblBook.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colGenres.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("AvailableStatus"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
    }

    private void generateBookId() {
        try {
            String id = bookBO.generateBookID();
            txtBookID.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearField() {
        txtBookID.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtCountry.clear();
        txtQty.clear();
        cmbAvailability.setValue(null);
        cmbGenre.setValue(null);
        cmbBranchId.setValue(null);
    }
}
