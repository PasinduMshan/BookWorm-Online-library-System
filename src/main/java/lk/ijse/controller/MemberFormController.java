package lk.ijse.controller;

import com.jfoenix.controls.JFXTextArea;
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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MemberBO;
import lk.ijse.dto.MemberDto;
import lk.ijse.tm.MemberTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MemberFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private TableView<MemberTm> tblMember;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtMemberID;

    @FXML
    private JFXTextField txtName;

    MemberBO memberBO = (MemberBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEMBER);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String memberID = txtMemberID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String date = String.valueOf(dpDateOfBirth.getValue());

        boolean isValidate = validateAdminDetails(name, address, email, contact);

        if (isValidate) {
            MemberDto dto = new MemberDto(memberID, name, address, email, contact, date);

            try {
                boolean isSaved = memberBO.addMember(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION,"Member Save Success!!!").showAndWait();
                    clearField();
                    loadAllMembers();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Save Failed!!!").showAndWait();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtMemberID.getText();

        try {
            boolean isDeleted = memberBO.deleteMember(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Delete Success!!!").showAndWait();
                clearField();
                loadAllMembers();
            } else  {
                new Alert(Alert.AlertType.ERROR,"Delete Failed!!!").showAndWait();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String contact = txtContact.getText();

        try {
            MemberDto dto = memberBO.searchMember(contact);

            if (dto != null) {
                txtMemberID.setText(dto.getMemberId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtEmail.setText(dto.getEmail());
                txtContact.setText(dto.getContact());
                dpDateOfBirth.setValue(LocalDate.parse(dto.getD_O_B()));
            } else {
                new Alert(Alert.AlertType.ERROR,"Member Not Found!!!").showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String memberID = txtMemberID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String date = String.valueOf(dpDateOfBirth.getValue());

        boolean isValidate = validateAdminDetails(name, address, email, contact);

        if (isValidate) {
            MemberDto dto = new MemberDto(memberID, name, address, email, contact, date);

            try {
                boolean isUpdated = memberBO.updateMember(dto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION,"Update Success!!!").showAndWait();
                    clearField();
                    loadAllMembers();
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
        generateMemberId();
        setCellValueFactory();
        loadAllMembers();
        tableListener();
    }

    private void tableListener() {
        tblMember.getSelectionModel().selectedItemProperty().addListener((observableValue, memberTm, t1) -> {
            try {
                MemberDto dto = memberBO.searchMemberById(t1.getMemberId());

                if (dto != null) {
                    txtMemberID.setText(dto.getMemberId());
                    txtName.setText(dto.getName());
                    txtAddress.setText(dto.getAddress());
                    txtEmail.setText(dto.getEmail());
                    txtContact.setText(dto.getContact());
                    dpDateOfBirth.setValue(LocalDate.parse(dto.getD_O_B()));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadAllMembers() {
        ObservableList<MemberTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<MemberDto> allMember = memberBO.getAllMember();

            for (MemberDto dto : allMember) {
                obList.add(new MemberTm(
                        dto.getMemberId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getContact()
                ));
            }
            tblMember.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
    }

    private void generateMemberId() {
        try {
            String Id = memberBO.generateMemberId();
            txtMemberID.setText(Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateAdminDetails(String name, String address, String email, String contact) {
        boolean matches = Pattern.matches("[A-Za-z.]+", name);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR,"Invalid Name!!").show();
            return false;
        }

        boolean matches1 = Pattern.matches("[A-Za-z0-9/,.\\s]+", address);
        if (!matches1) {
            new Alert(Alert.AlertType.ERROR,"Invalid Address!!").show();
            return false;
        }

        boolean matches2 = Pattern.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", email);
        if (!matches2) {
            new Alert(Alert.AlertType.ERROR,"Invalid Email!!").show();
            return false;
        }

        boolean matches3 = Pattern.matches("^(?:7|0|(?:\\+94))[0-9]{9,10}$", contact);
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR,"Invalid Contact!!").show();
            return false;
        }

        return true;
    }

    private void clearField() {
        txtMemberID.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
        dpDateOfBirth.setValue(null);
        generateMemberId();
    }

}
