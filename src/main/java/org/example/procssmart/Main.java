package org.example.procssmart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;


public class Main extends Application {

    public static  HashMap<String, String> userDatabase = new HashMap<>();

    public static PaymentDataManager dataManager;
    public static ThongkeDataManager thongkeDataManager;

    @Override
    public void start(Stage stage) throws Exception {
        userDatabase.put("anh", "111");
        dataManager = new PaymentDataManager();
        thongkeDataManager = new ThongkeDataManager();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("ProCSSmart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
