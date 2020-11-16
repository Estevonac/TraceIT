package Eventos;

import ABM.Ciudadano;
import Persistencia.GestorDeArchivos;
import java.io.IOException;
import java.util.ArrayList;

public class Brote implements GestorDeArchivos {

    public final String zona;
    private final int cantidadEnfermos;
    public ArrayList<Ciudadano> contagiados;
    public final String enfermedad;

    public Brote(String zona, ArrayList<Ciudadano> contagiados, int cantidadEnfermos, String enfermedad) throws IOException { // Empezar a modelar

        this.zona = zona;
        this.contagiados = contagiados;
        this.cantidadEnfermos = cantidadEnfermos;
        this.enfermedad = enfermedad;
        escribirArchivo("Brotes", enfermedad + "," + zona + "," + cantidadEnfermos); // Creo un brote y lo escribo


    }

    public String getZona(){
        return zona;
    }

    public String getEnfermedad() { return enfermedad; }

    public int getCantidadEnfermos() {
        return cantidadEnfermos;
    }

    public ArrayList<Ciudadano> cantidadContagiados(){
        return contagiados;
    }
}
