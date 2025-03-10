package org.example.procssmart;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteObjection {
    public static void main(String[] args) throws IOException {
        List<PaymentData> list = new ArrayList<>();

        FileOutputStream fos = new FileOutputStream("src/main/resources/org/example/procssmart/data.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    }
}
