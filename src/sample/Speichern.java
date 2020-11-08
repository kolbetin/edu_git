package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class Speichern {

    public static void main(String[] args) {
       new Speichern();
    }

    public void speichereListe(ArrayList<String> antworten) {
        try (OutputStream fos = new FileOutputStream("src/antworten.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(antworten);
            System.out.println("Liste gespeichert: " + antworten);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
