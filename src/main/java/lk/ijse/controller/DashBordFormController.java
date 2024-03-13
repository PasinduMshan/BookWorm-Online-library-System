package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBordFormController {

    @FXML
    private AnchorPane moveNode;

    @FXML
    private AnchorPane rootNode;

    @FXML
    void btnAdminOnAction(ActionEvent event) {

    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Book_Form.fxml"));
        this.moveNode.getChildren().clear();
        this.moveNode.getChildren().add(rootNode);
    }

    @FXML
    void btnBorrowReturnOnAction(ActionEvent event) {

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

}