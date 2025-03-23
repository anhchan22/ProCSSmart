package org.example.procssmart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThongkeDataManager {
    public List<ThongkeData> listDataThongke;
    public ThongkeDataManager() throws IOException, ClassNotFoundException {
        readThongkeData();
    }

    public void saveThongkeData() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/resources/org/example/procssmart/thongke.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listDataThongke);
        oos.close();
        fos.close();
    }

    public void readThongkeData() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("src/main/resources/org/example/procssmart/thongke.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        listDataThongke = (ArrayList<ThongkeData>) ois.readObject();
        ois.close();
        fin.close();
    }

    public void addData(int i, int j, String value) throws IOException, ClassNotFoundException {
        listDataThongke.add(new ThongkeData(i, j, value));
        saveThongkeData();
    }

    public List<ThongkeData> getListDataThongke() {
        return listDataThongke;
    }
}
