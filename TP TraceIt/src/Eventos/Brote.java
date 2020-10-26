package Eventos;

import ABM.Ciudadano;

import java.util.ArrayList;

public class Brote {

    public final String zona;
    public ArrayList<Ciudadano> contagiados;

    /*
    Utilizar las fechas. Necesitamos saber si en un encuentro se enfermaron algunas personas y los sintomas que contrajo
    Que fecha utilizamos? la inicial o la final. Acordarse que alguien enfermo no puede asistir.
     */
    public Brote(String zona, ArrayList<Ciudadano> contagiados){ // Empezar a modelar

        this.zona = zona;
        this.contagiados = contagiados;
    }

    public Brote surgioUnBrote(String zona, ArrayList<Ciudadano> contagiados){ // Re estructurar. Ahora es igual que constructor.

        return  new Brote(zona,contagiados);
    }


    public String getZona(){
        return zona;
    }
    public ArrayList<Ciudadano> cantidadContagiados(){
        return contagiados;
    }
}
