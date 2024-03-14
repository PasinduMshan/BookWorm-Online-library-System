package lk.ijse.controller;

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
import lk.ijse.bo.custom.AdminBO;
import lk.ijse.dto.AdminDto;
import lk.ijse.tm.AdminTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AdminFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private TableView<AdminTm> tblAdmins;

    @FXML
    private JFXTextField txtAdminId;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String adminID = txtAdminId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        boolean isValidate = validateAdminDetails(name,email,userName,password);

        if (isValidate) {
            AdminDto dto = new AdminDto(adminID, name, email, userName, password);

            try {
                boolean isSaved = adminBO.saveAdmin(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION,"Admin Save Success!!!").showAndWait();
                    clearField();
                    loadAllAdmin();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Save Failed!!!").showAndWait();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
            }
        }

    }


    private boolean validateAdminDetails(String name, String email, String userName, String password) {
        boolean matches = Pattern.matches("[A-Za-z.\\s]+", name);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR,"Invalid Name!!").show();
            return false;
        }

        boolean matches2 = Pattern.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", email);
        if (!matches2) {
            new Alert(Alert.AlertType.ERROR,"Invalid Email!!").show();
            return false;
        }

        boolean matches3 = Pattern.matches("[a-zA-Z0-9]{5,13}", userName);
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR,"Invalid UserName!!").show();
            return false;
        }

        boolean matches4 = Pattern.matches("^[a-zA-Z0-9]{4,}$", password);
        if (!matches4) {
            new Alert(Alert.AlertType.ERROR,"Invalid Password!!  \n\nOnly include (A-Z, a-z, 0-9) and ( at least 4 characters )").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtAdminId.getText();

        try {
            boolean isDeleted = adminBO.deleteAdmin(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Delete Success!!!").showAndWait();
                clearField();
                loadAllAdmin();
            } else  {
                new Alert(Alert.AlertType.ERROR,"Delete Failed!!!").showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        clearField();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtAdminId.getText();

        try {
            AdminDto dto = adminBO.searchAdmin(id);

            if (dto != null) {
                txtAdminId.setText(dto.getAdminId());
                txtName.setText(dto.getName());
                txtEmail.setText(dto.getEmail());
                txtUserName.setText(dto.getUserName());
                txtPassword.setText(dto.getPassword());
            } else {
                new Alert(Alert.AlertType.ERROR,"Admin Not Found!!!").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String adminID = txtAdminId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        boolean isValidate = validateAdminDetails(name,email,userName,password);

        if (isValidate) {
            AdminDto dto = new AdminDto(adminID, name, email, userName, password);

            try {
                boolean isUpdated = adminBO.updateAdmin(dto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION,"Update Success!!!").showAndWait();
                    clearField();
                    loadAllAdmin();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Update Failed!!!").showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextUserId();
        setCellValueFactory();
        loadAllAdmin();
        tableListener();
    }

    private void tableListener() {
        tblAdmins.getSelectionModel().selectedItemProperty().addListener((observableValue, adminTm, t1) -> {
            try {
                AdminDto adminDto = adminBO.searchAdmin(t1.getAdminId());

                if (adminDto != null) {
                    txtAdminId.setText(adminDto.getAdminId());
                    txtName.setText(adminDto.getName());
                    txtEmail.setText(adminDto.getEmail());
                    txtUserName.setText(adminDto.getUserName());
                    txtPassword.setText(adminDto.getPassword());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadAllAdmin() {
        ObservableList<AdminTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<AdminDto> allAdmin = adminBO.getAllAdmin();

            for (AdminDto adminDto : allAdmin) {
                obList.add(new AdminTm(
                        adminDto.getAdminId(),
                        adminDto.getName(),
                        adminDto.getEmail()
                ));
            }
            tblAdmins.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }

    private void generateNextUserId() {
        try {
            String id = adminBO.generateId();
            txtAdminId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearField() {
        txtAdminId.clear();
        txtName.clear();
        txtEmail.clear();
        txtUserName.clear();
        txtPassword.clear();
        generateNextUserId();
    }

}
