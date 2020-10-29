package Eventos;

import ABM.Ciudadano;
import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.ArrayList;

public class Brote implements GestorDeArchivos {

    public final String zona;
    private final int cantidadEnfermos;
    public ArrayList<Ciudadano> contagiados;

    /*
    Utilizar las fechas. Necesitamos saber si en un encuentro se enfermaron algunas personas y los sintomas que contrajo
    Que fecha utilizamos? la inicial o la final. Acordarse que alguien enfermo no puede asistir.
     */
    public Brote(String zona, ArrayList<Ciudadano> contagiados, int cantidadEnfermos, String enfermedad){ // Empezar a modelar

        this.zona = zona;
        this.contagiados = contagiados;
        this.cantidadEnfermos = cantidadEnfermos;
    }

    //Brote a partir de un encuentro
    public Brote surgioUnBrote(Enfermedad unaEnfermedad,Encuentro unEncuentro) throws IOException { // Re estructurar. Ahora es igual que constructor.
            escribirArchivo("Brotes", unEncuentro.getZona() + "," + unEncuentro.getCantEnfermosEnEncuentro(unaEnfermedad));
        return  new Brote(unEncuentro.getZona(), unEncuentro.getEnfermosEnEncuentro(unaEnfermedad),unEncuentro.getCantEnfermosEnEncuentro(unaEnfermedad), unaEnfermedad.getNombre());
    }

    //Brote a partir de un ArrayList de Ciudadanos
    public Brote surgioUnBrote(ArrayList<Ciudadano> enfermos, Enfermedad unaEnfermedad,String zona){

        return new Brote(zona,enfermos,enfermos.size(), unaEnfermedad.getNombre());
    }


    public String getZona(){
        return zona;
    }

    public int getCantidadEnfermos() {
        return cantidadEnfermos;
    }

    public ArrayList<Ciudadano> cantidadContagiados(){
        return contagiados;
    }
}
