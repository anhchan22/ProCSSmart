package org.example.procssmart;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadObjection {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<PaymentData> list = new ArrayList<>();

        FileInputStream fis = new FileInputStream("src/main/resources/org/example/procssmart/data.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (List<PaymentData>) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(list);
    }

}
