
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
import java.util.ResourceBundle;

import static org.example.procssmart.Main.dataManager;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane homePane, tienraPane, tienvaoPane;

    @FXML
    private ScrollPane thongkePane;

    @FXML
    private TableView<PaymentData> bangtienvao, bangtienra;

    @FXML
    private TableColumn<PaymentData, Integer> sttColumn, sttColumn2;

    @FXML
    private TableColumn<PaymentData, String> sotienColumn, sotienColumn2, noidungColumn, ngayColumn, ghichuColumn, noidungColumn2, ngayColumn2, ghichuColumn2;

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

    @FXML
    private Label tongsodu, tongtienvao, tongtienra;

    @FXML


    private  ObservableList<PaymentData> fullData;

    private void updateSummary() {
        int tongThu = 0;
        int tongChi = 0;

        for (PaymentData info : fullData) {
            if (info.getSotien() > 0) {
                tongThu += info.getSotien();
            } else {
                tongChi += info.getSotien();
            }
        }

        int soDu = tongThu - tongChi;

        tongsodu.setText(String.format("%,d", tongThu) + " VND");
        tongtienra.setText(String.format("%,d", tongChi) + " VND");
        tongtienvao.setText(String.format("%,d", soDu) + " VND");
    }

    public void clickHome(ActionEvent event) {
        homePane.setVisible(true);
        themPane.setVisible(false);
        tienvaoPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);

        updateSummary();
    }

    private void updateTableTienVao() {

        ObservableList<PaymentData> filteredData = FXCollections.observableArrayList();

        String selectedMonth = chonthang.getValue();

        for (PaymentData info : fullData) {
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
    public void add (ActionEvent event) throws IOException {
        {
            PaymentData paymentData = new PaymentData();
            paymentData.setStt(Integer.parseInt(sttText.getText()));
            paymentData.setNoidung1(noidungText.getText());
            paymentData.setNgay(ngayText.getText());
            paymentData.setGhichu(ghichuText.getText());
            paymentData.setSotien(Integer.parseInt(sotienText.getText()));

            fullData.add(paymentData);
            dataManager.addInformation(paymentData);
            updateTableTienVao();
            updateTableTienRa();
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

        ObservableList<PaymentData> filteredData = FXCollections.observableArrayList();

        String selectedMonth = chonthang2.getValue();

        for (PaymentData info : fullData) {
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
        bangtienra.setItems(filteredData);
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
        fullData = FXCollections.observableArrayList(dataManager.getListInfor());

        // Cập nhật bảng
        bangtienvao.setItems(fullData);
        bangtienra.setItems(fullData);

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


        sttColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, Integer>("stt"));
        noidungColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("noidung1"));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ngay"));
        sotienColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedSotien()));
        ghichuColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ghichu"));


        sttColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, Integer>("stt"));
        noidungColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("noidung1"));
        ngayColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ngay"));
        sotienColumn2.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedSotien()));
        ghichuColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ghichu"));

        updateSummary();



        homePane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);
    }
}
