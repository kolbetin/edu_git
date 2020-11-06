package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserialisierung {

    public static void main(String[] args){
        ArrayList liste = null;

        try {
            InputStream fis = new FileInputStream("src/antworten.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            liste = (ArrayList) ois.readObject();
            System.out.println("Liste geladen " + liste);
            ois.close();

        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }

    }
}
