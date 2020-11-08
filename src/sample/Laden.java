package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class Laden {

    public ArrayList<String> liste;
    Random random;

    public static void main(String[] args) {
        ArrayList<String> liste = null;
    }


    public void laden(){
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

    public String getliste() {
        random = new Random();
        int index = random.nextInt(liste.size());
        return liste.get(index);
    }


}
