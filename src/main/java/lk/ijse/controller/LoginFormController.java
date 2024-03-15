package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.lib.AdminConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        boolean isValidate = validateAdminDetails(userName, password);

        try {
            if (isValidate) {
                boolean isLoginSuccess = loginBO.checkCredentials(userName, password);

                if (isLoginSuccess) {
                    new Alert(Alert.AlertType.INFORMATION, "Login Successful!!!\n\nWelcome, "+userName).showAndWait();

                    AdminConnection.getInstance().setUserName(userName);
                    AdminConnection.getInstance().setPassword(password);

                    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/DashBord_Form.fxml"));
                    Scene scene = new Scene(rootNode);
                    Stage stage = (Stage) this.rootNode.getScene().getWindow();
                    stage.setScene(scene);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Login Failed!!!\n\nPlease Check Credentials..").showAndWait();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private boolean validateAdminDetails(String userName, String password) {

        boolean matches3 = Pattern.matches("[a-zA-Z0-9]{5,13}", userName);
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR,"Invalid UserName!!").show();
            return false;
        }

        boolean matches4 = Pattern.matches("^[a-zA-Z0-9]{4,}$", password);
        if (!matches4) {
            new Alert(Alert.AlertType.ERROR,"Invalid Password!!  \n\nOnly include(A-Z,a-z,0-9) & (at least 4 characters)").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnSignUpOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/SignUp_Form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
    }

}