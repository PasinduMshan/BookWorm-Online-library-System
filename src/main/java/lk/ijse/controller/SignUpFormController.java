package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SignUpBO;
import lk.ijse.dto.AdminDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    SignUpBO signUpBO = (SignUpBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SIGNUP);

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        try {
            String adminID = signUpBO.generateNextUserId();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String userName = txtUserName.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            boolean isValidate = validateAdminDetails(name,email,userName,password,confirmPassword);

            if (isValidate) {
                if (!password.equals(confirmPassword)) {
                    new Alert(Alert.AlertType.ERROR, "Password does not match with confirm password!!!").showAndWait();
                    return;
                }

                AdminDto dto = new AdminDto(adminID, name, email, userName, password);

                boolean isAdd = signUpBO.addAdmin(dto);

                if (isAdd) {
                    clearFields();
                    new Alert(Alert.AlertType.INFORMATION,"Registration Successful!!!").showAndWait();

                    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Login_Form.fxml"));

                    Scene scene = new Scene(rootNode);
                    Stage stage = (Stage) this.rootNode.getScene().getWindow();
                    stage.setScene(scene);
                } else {
                    new Alert(Alert.AlertType.ERROR,"Registration Not Success!!!").showAndWait();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtName.clear();
        txtEmail.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

    private boolean validateAdminDetails(String name, String email, String userName, String password, String confirmPassword) {
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
            new Alert(Alert.AlertType.ERROR,"Invalid Password!!  \n\nOnly include(A-Z,a-z,0-9) & (at least 4 characters)").show();
            return false;
        }

        boolean matches5 = Pattern.matches("^[a-zA-Z0-9]{4,}$", confirmPassword);
        if (!matches5) {
            new Alert(Alert.AlertType.ERROR,"Invalid Password!!  Only include(A-Z,a-z,0-9) & (at least 4 characters)").show();
            return false;
        }

        return true;
    }

}