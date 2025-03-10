package org.example.procssmart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main extends Application {

    public static  HashMap<String, String> userDatabase = new HashMap<>();

    public static DataManager dataManager;
    @Override
    public void start(Stage stage) throws Exception {
        userDatabase.put("anh", "111");
        dataManager = new DataManager();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
//
//public DataManager() throws Exception {
//    Information infor = new Information(1, "noi dung", 10000, "22/05/2025", "");
//    listInfor.add(infor);
//    readData();
//}
//
//public void saveData() throws IOException {
//    FileOutputStream fos = new FileOutputStream("src/main/resources/org/example/procssmart/data.txt");
//    ObjectOutputStream oos = new ObjectOutputStream(fos);
//    oos.writeObject(listInfor);
//    oos.close();
//    fos.close();
//}
//
//public void readData() throws IOException, ClassNotFoundException {
//    FileInputStream fis = new FileInputStream("src/main/resources/org/example/procssmart/data.txt");
//    ObjectInputStream ois = new ObjectInputStream(fis);
//    listInfor = (ArrayList<Information>) ois.readObject();
//    ois.close();
//    fis.close();
//}
//
//public void addInformation(Information info) throws IOException {
//
//    listInfor.add(info);
//    saveData();
//}
//
//public void removeInformation(Information info) throws IOException {
//    listInfor.remove(info);
//}
//public List<Information> getListInfor() {
//    return listInfor;
//}