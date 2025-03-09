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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;



public class HomeController implements Initializable {

    @FXML
    private AnchorPane homePane, tienraPane, tienvaoPane;

    @FXML
    private ScrollPane thongkePane;

    @FXML
    private TableView<Information> bangtienvao, bangtienra;

    @FXML
    private TableColumn<Information, Integer> sttColumn, sttColumn2;

    @FXML
    private TableColumn<Information, String> sotienColumn, sotienColumn2, noidungColumn, ngayColumn, ghichuColumn, noidungColumn2, ngayColumn2, ghichuColumn2;

    @FXML
    private TextField sttText, noidungText, sotienText, ngayText, ghichuText;

    @FXML
    private Button themButton, nutthem;

    @FXML
    private AnchorPane themPane;

    @FXML
    private ComboBox<String> chonthang;

    @FXML
    private ComboBox<String> chonthang2;

    private  ObservableList<Information> fullData;


    public void clickHome(ActionEvent event) {
        homePane.setVisible(true);
        themPane.setVisible(false);
        tienvaoPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);
    }

    private void updateTableTienVao() {

        ObservableList<Information> filteredData = FXCollections.observableArrayList();

        String selectedMonth = chonthang.getValue();

        for (Information info : fullData) {
            if (info.getSotien() > 0) {
                if (selectedMonth != null) {
                    int selectedMonthNum = Integer.parseInt(selectedMonth.split(" ")[1]);
                    int infoMonth = Integer.parseInt(info.getNgay().split("/")[1]);
                    if (infoMonth == selectedMonthNum) {
                        filteredData.add(info);
                    }
                } else {
                    filteredData.add(info);
                }
            }
        }

        bangtienvao.setItems(filteredData);
    }

    public void clickTienvao(ActionEvent event) {
        tienvaoPane.setVisible(true);
        tienraPane.setVisible(false);
        themPane.setVisible(false);
        homePane.setVisible(false);
        thongkePane.setVisible(false);

        updateTableTienVao();
    }

    @FXML
    public void add (ActionEvent event) {
        {
            Information information = new Information();
            information.setStt(Integer.parseInt(sttText.getText()));
            information.setNoidung1(noidungText.getText());
            information.setNgay(ngayText.getText());
            information.setGhichu(ghichuText.getText());
            information.setSotien(Integer.parseInt(sotienText.getText()));

            fullData.add(information);

            sttText.clear();
            noidungText.clear();
            ngayText.clear();
            sotienText.clear();
            ghichuText.clear();

            themPane.setVisible(false);
        }
    }

    public void clickThem(ActionEvent event) {
        homePane.setVisible(true);
        themPane.setVisible(true);

    }

    private void updateTableTienRa() {

        ObservableList<Information> filteredData = FXCollections.observableArrayList();

        String selectedMonth = chonthang2.getValue();

        for (Information info : fullData) {
            if (info.getSotien() < 0) {
                if (selectedMonth != null) {
                    int selectedMonthNum = Integer.parseInt(selectedMonth.split(" ")[1]); // Lấy số tháng
                    int infoMonth = Integer.parseInt(info.getNgay().split("/")[1]); // Lấy tháng từ ngày
                    if (infoMonth == selectedMonthNum) {
                        filteredData.add(info);
                    }
                } else {
                    filteredData.add(info);
                }
            }
        }

        bangtienra.setItems(filteredData); // Cập nhật bảng
    }

    public void clickTienra(ActionEvent event) {
        tienraPane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        homePane.setVisible(false);
        thongkePane.setVisible(false);

        updateTableTienRa();
    }



    public void clickThongke(ActionEvent event) {
        thongkePane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        homePane.setVisible(false);
        tienraPane.setVisible(false);
    }

    public void clickBaocao(ActionEvent event) {

    }

    public void clickDangxuat(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        fullData = FXCollections.observableArrayList(
                new Information(1, "quy thang 1", 100000, "22/01/2005", " "),
                new Information(2, "Thu tiền áo", 200000, "10/02/2025", " "),
                new Information(4, "GCC chuyển tiền nhà", 560000, "22/03/2025", "done"),
                new Information(3, "Thanh toán tiền nhà t1", -1444000, "22/01/2005", " ")
        );

        chonthang.setItems(FXCollections.observableArrayList(
                 "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        ));

        chonthang.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateTableTienVao(); // Gọi lại hàm lọc mỗi khi chọn tháng mới
        });

        chonthang2.setItems(FXCollections.observableArrayList(
                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"

        ));
        chonthang2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateTableTienRa();
        });


        sttColumn.setCellValueFactory(new PropertyValueFactory<Information, Integer>("stt"));
        noidungColumn.setCellValueFactory(new PropertyValueFactory<Information, String>("noidung1"));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<Information, String>("ngay"));
        sotienColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedSotien()));
        ghichuColumn.setCellValueFactory(new PropertyValueFactory<Information, String>("ghichu"));


        sttColumn2.setCellValueFactory(new PropertyValueFactory<Information, Integer>("stt"));
        noidungColumn2.setCellValueFactory(new PropertyValueFactory<Information, String>("noidung1"));
        ngayColumn2.setCellValueFactory(new PropertyValueFactory<Information, String>("ngay"));
        sotienColumn2.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedSotien()));
        ghichuColumn2.setCellValueFactory(new PropertyValueFactory<Information, String>("ghichu"));

        homePane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);
    }
}
