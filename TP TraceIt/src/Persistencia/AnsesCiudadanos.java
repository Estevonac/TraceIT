package Persistencia;

import java.io.*;
import java.util.ArrayList;

public class AnsesCiudadanos {

    ArrayList<String> cuilsCiudadanos;
    ArrayList<String> telefonosCiudadanos;
    ArrayList<String> zonasCiudadanos;

    public AnsesCiudadanos() throws IOException {
        this.cuilsCiudadanos = new ArrayList<>();
        this.telefonosCiudadanos = new ArrayList<String>();
        this.zonasCiudadanos = new ArrayList<>();

    }

    public void datosALeer(String leerDesde) throws IOException { this.leerArchivos("src/Datasets/" + leerDesde + ".txt"); }

    public void datosAEscribir(String escribirDesde) throws IOException { this.escribirArchivos("src/Datasets/" + escribirDesde + ".txt"); }


    public void leerArchivos(String file) throws IOException { //Hacerlo mas generico

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String head = bufferedReader.readLine();
        String line;
        if (file.equalsIgnoreCase("AnsesCiudadanos")) { // Vemos que archivo vamos a leer. Buscar forma de achicar el metodo

            while ((line = bufferedReader.readLine()) != null) {
                String[] user = line.split(",");
                cuilsCiudadanos.add(user[0]);
                telefonosCiudadanos.add(user[1]);
                zonasCiudadanos.add(user[2]);
            }
        }
        bufferedReader.close();
    }

    public void escribirArchivos(String file) throws IOException {


    }
}
