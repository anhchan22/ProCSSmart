package org.example.procssmart;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static org.example.procssmart.Main.userDatabase;

public class LoginController implements Initializable {


    @FXML
    private PasswordField dienmk;

    @FXML
    private TextField dienten;

    @FXML
    private VBox dangnhapBox;

    @FXML
    private VBox dangkiBox;

    @FXML
    private TextField taotk;

    @FXML
    private TextField taomk;

    @FXML
    private TextField dienlaimk;

    public void changeDangnhap (ActionEvent event) throws IOException {
        String mk = dienmk.getText().toString();
        String tk = dienten.getText().toString();

        if(userDatabase.containsKey(tk) && userDatabase.containsValue(mk) ) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kiểm tra lại tài khoản và mật khẩu!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void changeDangki (ActionEvent event) {
        dangnhapBox.setVisible(false);
        dangkiBox.setVisible(true);
    }

    public void clickDangki2 (ActionEvent event) {
        String mk = taomk.getText().toString();
        String tk = taotk.getText().toString();
        String nhaplaimk = dienlaimk.getText().toString();

        if(userDatabase.containsKey(tk)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tài khoản đã tồn tại!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            if(mk.equals(nhaplaimk) && !tk.isEmpty() && !mk.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Đăng ký tài khoản thành công!");
                alert.setHeaderText(null);
                alert.showAndWait();
                userDatabase.put(tk, mk);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mật khẩu không trùng khớp!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    public void clickQuayve (ActionEvent event)   {
        dangkiBox.setVisible(false);
        dangnhapBox.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dangnhapBox.setVisible(true);
        dangkiBox.setVisible(false);
    }
}
