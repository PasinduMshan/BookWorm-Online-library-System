package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dto.QueryDto;
import lk.ijse.lib.AdminConnection;
import lk.ijse.tm.QueryReturnLateTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashBordFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblBranchCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblMemberCount;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUserName;

    @FXML
    private TableView<QueryReturnLateTm> tblReturnLate;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private AnchorPane rootNode;

    DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);

    @FXML
    void btnAdminOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Admin_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Book_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @FXML
    void btnBorrowReturnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Borrow_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @FXML
    void btnBranchesOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Branches_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/DashBord_Form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Login_Form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Member_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @FXML
    void btnMemberHistoryOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/MemberHistory_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllLateReturns();
        setDateAndTime();
        setUserName();
        setBooKCount();
        setMemberCount();
        setBranchCount();

    }

    private void setBranchCount() {
        try {
            String branchCount = dashBoardBO.getBranchCount();
            lblBranchCount.setText(branchCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setMemberCount() {
        try {
            String memberCount = dashBoardBO.getMemberCount();
            lblMemberCount.setText(memberCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBooKCount() {
        try {
            String bookCount = dashBoardBO.getBookCount();
            lblBookCount.setText(bookCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUserName() {
        String userName = AdminConnection.getInstance().getUserName();
        String password = AdminConnection.getInstance().getPassword();

        String adminName = null;
        try {
            adminName = dashBoardBO.getAdminName(userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblUserName.setText(adminName);
    }

    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        lblTime.setText(formattedTime);
    }

    private void setDateAndTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadAllLateReturns() {
        ObservableList<QueryReturnLateTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<QueryDto> queryDtos = dashBoardBO.loadAllLateReturn();

            for (QueryDto queryDto : queryDtos) {
                obList.add(new QueryReturnLateTm(
                        queryDto.getMemberId(),
                        queryDto.getMemberName(),
                        queryDto.getBookName(),
                        queryDto.getBorrowDate(),
                        queryDto.getReturnDate()
                ));
            }
            tblReturnLate.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("BorrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    }
}