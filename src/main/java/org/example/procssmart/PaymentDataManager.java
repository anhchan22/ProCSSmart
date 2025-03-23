package org.example.procssmart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataManager {
    private List<PaymentData> listInfor = new ArrayList<>();


    public PaymentDataManager() throws Exception {
//        Information infor = new Information(1, "noi dung", 10000, "22/05/2025", "");
//        listInfor.add(infor);
        readData();
    }

    public void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/resources/org/example/procssmart/data.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listInfor);
        oos.close();
        fos.close();
    }

    public void readData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/main/resources/org/example/procssmart/data.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        listInfor = (ArrayList<PaymentData>) ois.readObject();
        ois.close();
        fis.close();
    }

    public void addInformation(PaymentData info) throws IOException {
        listInfor.add(info);
        saveData();
    }

    public void updateInformation(PaymentData newInfo) throws IOException {
        for (int i = 0; i < listInfor.size(); i++) {
            if (listInfor.get(i).equals(newInfo)) {
                listInfor.set(i, newInfo);
                saveData(); // Lưu lại file
                return;
            }
        }
    }

    public void removeInformation(PaymentData info) throws IOException {
        listInfor.remove(info);
        saveData();
    }
    public List<PaymentData> getListInfor() {
        return listInfor;
    }
}
