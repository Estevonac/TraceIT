package Persistencia;

import java.io.*;
import java.util.ArrayList;

public class AnsesCiudadanos implements GestorDeArchivos{

    ArrayList<String> cuilsCiudadanos;
    ArrayList<String> telefonosCiudadanos;
    ArrayList<String> zonasCiudadanos;

    public AnsesCiudadanos() throws IOException {
        this.cuilsCiudadanos = new ArrayList<>();
        this.telefonosCiudadanos = new ArrayList<String>();
        this.zonasCiudadanos = new ArrayList<>();

    }




}
