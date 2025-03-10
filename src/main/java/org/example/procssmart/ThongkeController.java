package org.example.procssmart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ThongkeController implements Initializable {

    @FXML private GridPane gridPane;  // GridPane chứa các CheckBox
    @FXML private Label totalLabel;   // Label hiển thị tổng tiền

    private final String FILE_NAME = "checkBoxState.dat";  // Tên file lưu trạng thái
    private final int TIEN_DONG = 100000;  // Số tiền mỗi tháng (ví dụ: 100k)

    private Map<String, Boolean> checkBoxStates = new HashMap<>(); // Lưu trạng thái checkbox


    // Gán sự kiện cho CheckBox để cập nhật tổng tiền khi tick
    private void setupCheckBoxListeners() {
        for (var node : gridPane.getChildren()) {
            if (node instanceof CheckBox checkBox) {
                checkBox.setOnAction(event -> {
                    saveCheckBoxState();
                    updateTotal();
                });
            }
        }
    }

    // Tính tổng số tiền đã đóng (số lượng CheckBox được tick)
    private void updateTotal() {
        int count = 0;
        for (var node : gridPane.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                count++;
            }
        }
        totalLabel.setText(String.valueOf(count * TIEN_DONG)); // Hiển thị tổng tiền
    }

    // Lưu trạng thái CheckBox vào file
    private void saveCheckBoxState() {
        for (var node : gridPane.getChildren()) {
            if (node instanceof CheckBox checkBox) {
                String key = getCheckBoxKey(checkBox);
                checkBoxStates.put(key, checkBox.isSelected());
            }
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(checkBoxStates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc trạng thái CheckBox từ file
    private void loadCheckBoxState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // Nếu file chưa tồn tại thì bỏ qua

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            checkBoxStates = (Map<String, Boolean>) in.readObject();
            for (var node : gridPane.getChildren()) {
                if (node instanceof CheckBox checkBox) {
                    String key = getCheckBoxKey(checkBox);
                    checkBox.setSelected(checkBoxStates.getOrDefault(key, false));
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Lấy key duy nhất cho mỗi CheckBox (dựa vào vị trí trong GridPane)
    private String getCheckBoxKey(CheckBox checkBox) {
        Integer row = GridPane.getRowIndex(checkBox);
        Integer col = GridPane.getColumnIndex(checkBox);
        return "checkBox_" + (row == null ? 0 : row) + "_" + (col == null ? 0 : col);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCheckBoxState(); // Tải dữ liệu khi mở ứng dụng
        setupCheckBoxListeners(); // Gán sự kiện cho các CheckBox
        updateTotal(); // Cập nhật tổng tiền ban đầu
    }
}
