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
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDto;
import lk.ijse.tm.BranchTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BranchesFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private TableView<BranchTm> tblBranches;

    @FXML
    private JFXTextField txtBranchID;

    @FXML
    private JFXTextField txtName;

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String Id = txtBranchID.getText();
        String Name = txtName.getText();

        boolean isValidate = validateDetails(Name);

        if (isValidate) {
            BranchDto dto = new BranchDto(Id, Name);

            try {
                boolean isSaved = branchBO.saveBranch(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION,"Save Success!!!").showAndWait();
                    clearField();
                    loadAllBranches();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Save Failed!!!").showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateDetails(String name) {
        boolean matches = Pattern.matches("[A-Za-z0-9.]+", name);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR,"Invalid Name!!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String Id = txtBranchID.getText();

        try {
            boolean isDeleted = branchBO.deleteBranch(Id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Delete Success!!!").showAndWait();
                clearField();
                loadAllBranches();
            } else  {
                new Alert(Alert.AlertType.ERROR,"Delete Failed!!!").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtBranchID.getText();

        try {
            BranchDto dto = branchBO.searchBranch(id);

            if (dto != null) {
                txtBranchID.setText(dto.getBranchId());
                txtName.setText(dto.getName());
            } else {
                new Alert(Alert.AlertType.ERROR,"Branch Not Found!!!").showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Id = txtBranchID.getText();
        String Name = txtName.getText();

        boolean isValidate = validateDetails(Name);

        if (isValidate) {
            BranchDto dto = new BranchDto(Id, Name);

            try {
                boolean isUpdated = branchBO.updateBranch(dto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION,"Update Success!!!").showAndWait();
                    clearField();
                    loadAllBranches();
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
        generateBranchId();
        setCellValueFactory();
        loadAllBranches();
        tableListener();
    }

    private void tableListener() {
        tblBranches.getSelectionModel().selectedItemProperty().addListener((observableValue, branchTm, t1) -> {
            try {
                BranchDto dto = branchBO.searchBranch(t1.getBranchId());

                if (dto != null) {
                    txtBranchID.setText(dto.getBranchId());
                    txtName.setText(dto.getName());
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadAllBranches() {
        ObservableList<BranchTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<BranchDto> allBranches = branchBO.getAllBranches();

            for (BranchDto branchDto : allBranches) {
                obList.add(new BranchTm(
                        branchDto.getBranchId(),
                        branchDto.getName()
                ));
            }
            tblBranches.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("BranchId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    }

    private void generateBranchId() {
        try {
            String Id = branchBO.generateBranchId();
            txtBranchID.setText(Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearField() {
        txtBranchID.clear();
        txtName.clear();
        generateBranchId();
    }
}
