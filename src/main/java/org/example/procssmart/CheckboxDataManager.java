package org.example.procssmart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckboxDataManager {
    List<CheckboxData> checkboxList = new ArrayList<CheckboxData>();


    public CheckboxDataManager() throws IOException, ClassNotFoundException {

        readStatus();

    }


    public void saveStatus() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/resources/org/example/procssmart/checkbox.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(checkboxList);
        oos.close();
        fos.close();
    }

    public void readStatus() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("src/main/resources/org/example/procssmart/checkbox.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        checkboxList = (ArrayList<CheckboxData>) ois.readObject();
        ois.close();
        fin.close();
    }


}
