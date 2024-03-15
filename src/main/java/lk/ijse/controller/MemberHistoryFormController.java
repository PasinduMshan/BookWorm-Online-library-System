package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MemberHistoryBO;
import lk.ijse.dto.MemberDto;
import lk.ijse.dto.QueryDto;
import lk.ijse.tm.MemberHistoryTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MemberHistoryFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private TableView<MemberHistoryTm> tblHistory;

    @FXML
    private JFXTextField txtMemberId;

    @FXML
    private JFXTextField txtMemberName;

    MemberHistoryBO memberHistoryBO = (MemberHistoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEMBERHISTORY);

    @FXML
    void btnMemberHistoryOnAction(ActionEvent event) {
        ObservableList<MemberHistoryTm> obList = FXCollections.observableArrayList();

        String id = txtMemberId.getText();

        String name = null;
        txtMemberName.setText(name);
        try {
            List<QueryDto> allTransaction = memberHistoryBO.getAllTransaction(id);

            for (QueryDto queryDto : allTransaction) {
                obList.add(new MemberHistoryTm(
                        queryDto.getMemberId(),
                        queryDto.getBookName(),
                        queryDto.getBorrowDate(),
                        queryDto.getReturnDate(),
                        queryDto.getStatus()
                ));
            }
            tblHistory.setItems(obList);


            MemberDto dto = memberHistoryBO.getMemberName(id);

            if (dto != null) {
                txtMemberName.setText(dto.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        txtMemberId.clear();
        txtMemberName.clear();
        setCellValueFactory();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("BorrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }
}
