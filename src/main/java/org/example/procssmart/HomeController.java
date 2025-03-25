
package org.example.procssmart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
//
//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.header;
import static org.example.procssmart.Main.dataManager;
import static org.example.procssmart.Main.thongkeDataManager;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane homePane, tienraPane, tienvaoPane, baocaoPane;

    @FXML
    private ScrollPane thongkePane;

    @FXML
    private TableView<PaymentData> bangtienvao, bangtienra;

    @FXML
    private TableColumn<PaymentData, String> sotienColumn, sotienColumn2, noidungColumn, ngayColumn, ghichuColumn, noidungColumn2, ngayColumn2, ghichuColumn2;

    @FXML
    private TextField  noidungText, sotienText, ghichuText;

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
    private GridPane thongkeGrid;

    @FXML
    DatePicker selectDate;


    private  ObservableList<PaymentData> fullData;

             /* XỬ LÝ HOME */

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

        int soDu = tongThu + tongChi;

        tongsodu.setText(String.format("%,d", soDu) + " đ");
        tongtienra.setText(String.format("%,d", tongChi) + " đ");
        tongtienvao.setText(String.format("%,d", tongThu) + " đ");
    }

    public void clickHome(ActionEvent event) {
        homePane.setVisible(true);
        themPane.setVisible(false);
        tienvaoPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);
        baocaoPane.setVisible(false);

        updateSummary();
    }

            /* XỬ LÝ TIỀN VÀO */

    private void updateTableTienVao() {

        ObservableList<PaymentData> filteredData = FXCollections.observableArrayList();

        String selectedMonth = chonthang.getValue();

        for (PaymentData info : fullData) {
            if (info.getSotien() > 0) {
                if (selectedMonth != null) {
                    int selectedMonthNum = Integer.parseInt(selectedMonth.split(" ")[1]);
                    int infoMonth = Integer.parseInt(info.getNgay().split("-")[1]);
                    if (infoMonth == selectedMonthNum) {
                        filteredData.add(info);
                    }
                } else {
                    filteredData.add(info);
                }
            }
        }

        bangtienvao.setItems(filteredData);
        enableEditingTienVao();

    }

    private void enableEditingTienVao() {
        noidungColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        noidungColumn.setOnEditCommit(event -> {
            event.getRowValue().setNoidung1(event.getNewValue());
            try {
                dataManager.updateInformation(event.getRowValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        sotienColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sotienColumn.setOnEditCommit(event -> {
            try {
                int newValue = Integer.parseInt(event.getNewValue().replace(".", ""));

                NumberFormat formatter = NumberFormat.getInstance(Locale.US);
                String formattedValue = formatter.format(newValue);

                event.getRowValue().setSotien(newValue);

                bangtienvao.refresh();

                dataManager.updateInformation(event.getRowValue());
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        });

        ngayColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ngayColumn.setOnEditCommit(event -> {
            event.getRowValue().setNgay(event.getNewValue());
            try {
                dataManager.updateInformation(event.getRowValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ghichuColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ghichuColumn.setOnEditCommit(event -> {
            event.getRowValue().setGhichu(event.getNewValue());
            try {
                dataManager.updateInformation(event.getRowValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public void clickTienvao(ActionEvent event) {
        tienvaoPane.setVisible(true);
        tienraPane.setVisible(false);
        themPane.setVisible(false);
        homePane.setVisible(false);
        thongkePane.setVisible(false);
        baocaoPane.setVisible(false);

        updateTableTienVao();
//        bangtienvao.setEditable(true);
    }

    @FXML
    public void add (ActionEvent event) throws IOException {
        {
            PaymentData paymentData = new PaymentData();
            paymentData.setNoidung1(noidungText.getText());
            paymentData.setNgay(selectDate.getValue().toString());
            paymentData.setGhichu(ghichuText.getText());
            paymentData.setSotien(Integer.parseInt(sotienText.getText()));

            fullData.add(paymentData);
            dataManager.addInformation(paymentData);
            updateTableTienVao();
            updateTableTienRa();
            noidungText.clear();
//            selectDate.clear();
            sotienText.clear();
            ghichuText.clear();

            themPane.setVisible(false);

        }
    }

    public void clickThem(ActionEvent event) {
        homePane.setVisible(true);
        themPane.setVisible(true);

    }

                  /* XỬ LÝ TIỀN RA */

    private void updateTableTienRa() {

        ObservableList<PaymentData> filteredData = FXCollections.observableArrayList();

        String selectedMonth = chonthang2.getValue();

        for (PaymentData info : fullData) {
            if (info.getSotien() < 0) {
                if (selectedMonth != null) {
                    int selectedMonthNum = Integer.parseInt(selectedMonth.split(" ")[1]); // Lấy số tháng
                    int infoMonth = Integer.parseInt(info.getNgay().split("-")[1]); // Lấy tháng từ ngày
                    if (infoMonth == selectedMonthNum) {
                        filteredData.add(info);
                    }
                } else {
                    filteredData.add(info);
                }
            }
        }
        bangtienra.setItems(filteredData);
        enableEditingTienRa();
    }

    private void enableEditingTienRa() {
        noidungColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        noidungColumn2.setOnEditCommit(event -> {
            event.getRowValue().setNoidung1(event.getNewValue());
            try {
                dataManager.updateInformation(event.getRowValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sotienColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        sotienColumn2.setOnEditCommit(event -> {
            try {
                int newValue = Integer.parseInt(event.getNewValue().replace(".", ""));

                NumberFormat formatter = NumberFormat.getInstance(Locale.US);
                String formattedValue = formatter.format(newValue);

                event.getRowValue().setSotien(newValue);

                bangtienvao.refresh();

                dataManager.updateInformation(event.getRowValue());
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        });

        ngayColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        ngayColumn2.setOnEditCommit(event -> {
            event.getRowValue().setNgay(event.getNewValue());
            try {
                dataManager.updateInformation(event.getRowValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ghichuColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        ghichuColumn2.setOnEditCommit(event -> {
            event.getRowValue().setGhichu(event.getNewValue());
            try {
                dataManager.updateInformation(event.getRowValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void clickTienra(ActionEvent event) {
        tienraPane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        homePane.setVisible(false);
        thongkePane.setVisible(false);
        baocaoPane.setVisible(false);

        updateTableTienRa();
    }

                /* XỬ LÝ THỐNG KÊ */


    private TextField[][] tfMoney;
    private Label[] tongtientungthang;


    private void setupLabels() throws IOException, ClassNotFoundException {
        Label sttHeader = new Label("STT");
        Label hoTenHeader = new Label("Họ Tên");
        Label tongTienHeader = new Label("Tổng tiền");

        sttHeader.getStyleClass().add("vien-tren-trai");
        tongTienHeader.getStyleClass().add("vien-duoi-phai");
        sttHeader.setMinWidth(40);
        hoTenHeader.setMinWidth(162.400048828125);
        tongTienHeader.setMinWidth(238.400048828125);
        sttHeader.getStyleClass().add("label-header");
        hoTenHeader.getStyleClass().add("label-header");
        tongTienHeader.getStyleClass().add("label-header");

        thongkeGrid.add(sttHeader, 0, 0);
        thongkeGrid.add(hoTenHeader, 1, 0);
        thongkeGrid.add(tongTienHeader, 1, 55);

        String[] danhSachHoTen = {
                "Đỗ Thị Vân Anh", "Hoàng Duy Anh", "Lê Hoàng Anh", "Nguyễn Đức Anh", "Vương Trí Bách", "Phạm Hữu Chiến",
                "Trần Kiều Minh Dũng", "Nguyễn Thành Đạt", "Lê Đình Hiếu", "Trần Trung Hiếu", "Nguyễn Viết Hoàn", "Dương Đỗ Hoàng",
                "Đỗ Phạm Bảo Hoàng", "Trần Huy Hoàng", "Nguyễn Quốc Khánh", "Trần Ngọc Mai", "Nguyễn Lê Minh", "Trần Quang Minh",
                "Nguyễn Hạnh Nhi", "Hà Hoài Phương", "Nguyễn Văn Quang", "Nguyễn Văn Thành", "Nguyễn Tiến Thịnh", "Lê Thị Thủy",
                "Hà Tiến Triệu", "Nguyễn Hoàng Trung", "Bùi Đức Tuân", "Nguyễn Hoàng Tùng", "Phạm Hoàng Uyên", "Phạm Văn Vĩ",
                "Nguyễn Tuấn An", "Đỗ Đức Tuấn Anh", "Trần Ngọc Ánh", "Nguyễn Hoàng Biên", "Hoàng Văn Chính", "Lê Đình Chuyên",
               "Nguyễn Mạnh Dũng", "Phạm Mạnh Dũng", "Đỗ Vũ Hải Đăng", "Hán Hữu Đăng", "Nguyễn Nam Hải", "Giáp Minh Hiếu",
                "Nguyễn Danh Hòa", "Trần Lê Nam Khánh", "Phạm Mai Linh", "Văn Thị Mai Linh", "Trần Hải Long", "Trần Khắc Long",
                "Mông Thế Lực", "Nguyễn Văn Minh Lực", "Phan An Phúc", "Phạm Linh Thảo", "Nguyễn Trọng Toàn", "Nguyễn Thành Trung"
        };
        String[] danhsachThang = {
                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        };


        int rowCount = danhSachHoTen.length;
        int colCount = danhsachThang.length;

        tfMoney = new TextField[rowCount][colCount];
        tongtientungthang = new Label[colCount];

        for (int i = 0; i < danhsachThang.length; i++) {
            Label thangLabel = new Label(danhsachThang[i]);
            Label tongtienLabel = new Label("0");

            if(i==11){
                thangLabel.getStyleClass().add("vien-tren-phai");
                tongtienLabel.getStyleClass().add("vien-duoi-trai");
            }

            thangLabel.setMinWidth(100);
            tongtienLabel.setMinWidth(100);

            thangLabel.getStyleClass().add("label-header");
            tongtienLabel.getStyleClass().add("label-header");

            thongkeGrid.add(thangLabel, i + 2, 0);
            thongkeGrid.add(tongtienLabel, i + 2, 55);

            tongtientungthang[i] = tongtienLabel;
        }
        for (int i = 0; i < danhSachHoTen.length; i++) {
            Label sttLabel = new Label(String.valueOf(i + 1));
            Label hoTenLabel = new Label(danhSachHoTen[i]);


            sttLabel.setMinWidth(40);
            hoTenLabel.setMinWidth(162.400048828125);

            sttLabel.getStyleClass().add("grid-stt");
            hoTenLabel.getStyleClass().add("grid-name");

            thongkeGrid.add(sttLabel, 0, i + 1);
            thongkeGrid.add(hoTenLabel, 1, i + 1);
        }
        for (int i = 0; i < danhSachHoTen.length; i++) {
            for (int j = 0; j < danhsachThang.length; j++) {
                TextField tf = new TextField();

                tf.getStyleClass().add("grid-cell");
//                tf.setStyle("-fx-alignment: center; -fx-padding: 5px 0;");

                if (i % 2 == 1) {
                    tf.setStyle("-fx-background-color: #FCE4EC;");
                }

                for (ThongkeData data : thongkeDataManager.getListDataThongke()) {
                    if (data.getRow() == i && data.getCol() == j) {
                        tf.setText(data.getValue().replace(",", "").trim());
                        break;
                    }
                }

                tfMoney[i][j] = tf;


                int finalJ2 = j;
                int finalI2 = i;

                tf.setOnAction(event -> {
                    updateTotal();
                    saveData(finalI2, finalJ2, tf.getText());
                });


                thongkeGrid.add(tf, j + 2, i + 1);
            }
        }
    }

    private void saveData(int row, int col, String value) {
        try {
            thongkeDataManager.addData(row, col, value.replace(",", ""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateTotal() {
        for(int i = 0; i < 12; i++) {
            int sum = 0;
            for(int j = 0; j < 54; j++) {
                String value = tfMoney[j][i].getText();
                if (!value.isEmpty()) {
                    sum+=Integer.parseInt(value);
                }
            }
            tongtientungthang[i].setText(String.format("%,d", sum) + " đ");
        }
    }

    public void clickThongke(ActionEvent event) throws IOException, ClassNotFoundException {
        updateTotal();
        thongkePane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        homePane.setVisible(false);
        tienraPane.setVisible(false);
        baocaoPane.setVisible(false);
    }

                /* XỬ LÝ BÁO CÁO */

    @FXML
    private PieChart pieChart;

    @FXML
    private ComboBox<String> chonthangBaocao;

    @FXML
    private Label baocaoLabel;


    private double calculateTotalTienVao(String selectedMonth) {
        double total = 0;
        int selectedMonthNum = Integer.parseInt(selectedMonth.split(" ")[1]);

        for (PaymentData info : fullData) {
            int infoMonth = Integer.parseInt(info.getNgay().split("-")[1]);
            if (infoMonth == selectedMonthNum && info.getSotien() > 0) {
                total += info.getSotien();
            }
        }

        return total;
    }

    private double calculateTotalTienRa(String selectedMonth) {
        double total = 0;
        int selectedMonthNum = Integer.parseInt(selectedMonth.split(" ")[1]);

        for (PaymentData info : fullData) {
            int infoMonth = Integer.parseInt(info.getNgay().split("-")[1]);
            if (infoMonth == selectedMonthNum && info.getSotien() < 0) {
                total += Math.abs(info.getSotien());
            }
        }

        return total;
    }

    private void updatePieChart() {
        String selectedMonth = chonthangBaocao.getValue();
        if (selectedMonth != null) {
            double totalTienVao = calculateTotalTienVao(selectedMonth);
            double totalTienRa = calculateTotalTienRa(selectedMonth);
            double tienDu = totalTienVao - totalTienRa;

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Tiền Vào", totalTienVao),
                    new PieChart.Data("Tiền Ra", totalTienRa),
                    new PieChart.Data("Tiền Dư", tienDu)
            );

            pieChart.setData(pieChartData);
        }
    }

    private void updateBaocaoLabel() {
        String selectedMonth = chonthangBaocao.getValue();
        if (selectedMonth != null) {
            double totalTienVao = calculateTotalTienVao(selectedMonth);
            double totalTienRa = calculateTotalTienRa(selectedMonth);
            double tienDu = totalTienVao - totalTienRa;

            NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.US);

            String formattedTienVao = currencyFormat.format(totalTienVao) + " đ";
            String formattedTienRa = currencyFormat.format(totalTienRa) + " đ";
            String formattedTienDu = currencyFormat.format(tienDu) + " đ";

            String report = String.format(
                    "📅 BÁO CÁO THU - CHI %s\n\n" +
                            "💰 Tổng tiền thu vào: %s\n" +
                            "💸 Tổng tiền chi ra: %s\n" +
                            "💲 Tiền dư sau chi: %s\n\n",
                    selectedMonth, formattedTienVao, formattedTienRa, formattedTienDu
            );

            baocaoLabel.setText(report);
        }
    }

    public void clickBaocao(ActionEvent event) {
//        updatePieChart();
        baocaoPane.setVisible(true);
        tienvaoPane.setVisible(false);
        tienraPane.setVisible(false);
        themPane.setVisible(false);
        thongkePane.setVisible(false);

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
        bangtienvao.setEditable(true);
        bangtienra.setEditable(true);
        enableEditingTienVao();
//        enableEditingTienRa();
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


        noidungColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("noidung1"));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ngay"));
        sotienColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedSotien()));
        ghichuColumn.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ghichu"));

        noidungColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("noidung1"));
        ngayColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ngay"));
        sotienColumn2.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedSotien()));
        ghichuColumn2.setCellValueFactory(new PropertyValueFactory<PaymentData, String>("ghichu"));

        updateSummary();



        try {
            thongkeDataManager.readThongkeData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            setupLabels();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        chonthangBaocao.setItems(FXCollections.observableArrayList(
                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"

        ));

        chonthangBaocao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePieChart();
            updateBaocaoLabel();
        });

        homePane.setVisible(true);
        tienvaoPane.setVisible(false);
        themPane.setVisible(false);
        tienraPane.setVisible(false);
        thongkePane.setVisible(false);
        baocaoPane.setVisible(false);
    }
}
