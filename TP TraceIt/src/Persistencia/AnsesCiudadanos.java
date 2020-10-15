package Persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnsesCiudadanos {

    ArrayList<String> cuilsCiudadanos;
    ArrayList<String> telefonosCiudadanos;
    ArrayList<String> zonasCiudadanos;

    public AnsesCiudadanos() throws IOException {
        this.cuilsCiudadanos = new ArrayList<>();
        this.telefonosCiudadanos = new ArrayList<String>();
        this.zonasCiudadanos = new ArrayList<>();
        escribirArchivos("src/Datasets/AnsesCiudadanos.txt");
    }


    public void escribirArchivos(String file) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String head = bufferedReader.readLine();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] user = line.split(",");
            cuilsCiudadanos.add(user[0]);
            telefonosCiudadanos.add(user[1]);
            zonasCiudadanos.add(user[2]);
        }
        bufferedReader.close();
    }
}
