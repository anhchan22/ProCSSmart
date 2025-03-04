package org.example.procssmart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button dangki;

    @FXML
    private Button dangnhap;

    @FXML
    private PasswordField dienmk;

    @FXML
    private TextField dienten;

    @FXML
    private Label matkhau;

    @FXML
    private Label tendangnhap;

    @FXML
    private Label tieude;

    @FXML
    private AnchorPane pane;

    public void changeDangnhap (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeDangki (ActionEvent event) {

    }
}
