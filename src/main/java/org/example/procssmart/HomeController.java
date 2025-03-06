package org.example.procssmart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homePane;


    @FXML
    private AnchorPane tienraPane;

    @FXML
    private AnchorPane tienvaoPane;

    @FXML
    private ScrollPane thongkePane;

    @FXML
    private TableView<Information> bangtienvao;

    @FXML
    private TableColumn<Information, Integer> sttColumn;

    @FXML
    private TableColumn<Information, String> noidungColumn;

    @FXML
    private TableColumn<Information, Integer> sotienColumn;

    @FXML
    private TableColumn<Information, String> ngayColumn;

    @FXML
    private TableColumn<Information, String> ghichuColumn;

    private ObservableList<Information> data;

    public void clickHome(ActionEvent event) {
        homePane.setVisible(true);
        tienvaoPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);
    }

    public void clickTienvao(ActionEvent event) {
        tienvaoPane.setVisible(true);
        tienraPane.setVisible(false);
        homePane.setVisible(false);
        thongkePane.setVisible(false);
    }

    public void clickTienra(ActionEvent event) {
        tienraPane.setVisible(true);
        tienvaoPane.setVisible(false);
        homePane.setVisible(false);
        thongkePane.setVisible(false);
    }

    public void clickThongke(ActionEvent event) {
        thongkePane.setVisible(true);
        tienvaoPane.setVisible(false);
        homePane.setVisible(false);
        tienraPane.setVisible(false);
    }

    public void clickDangxuat(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList(
                new Information(1, "quy thang 1", 100000, "22/01/2005", " "),
                new Information(2, "Thu tiền áo", 200000, "10/02/2025", " ")
        );

        sttColumn.setCellValueFactory(new PropertyValueFactory<Information, Integer>("stt"));
        noidungColumn.setCellValueFactory(new PropertyValueFactory<Information, String>("noidung1"));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<Information, String>("ngay"));
        sotienColumn.setCellValueFactory(new PropertyValueFactory<Information, Integer>("sotien"));
        ghichuColumn.setCellValueFactory(new PropertyValueFactory<Information, String>("ghichu"));
        bangtienvao.setItems(data);

        homePane.setVisible(true);
        tienvaoPane.setVisible(false);
        tienvaoPane.setVisible(false);
        thongkePane.setVisible(false);
    }
}
