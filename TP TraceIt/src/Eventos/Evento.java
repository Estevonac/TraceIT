package Eventos;

import ABM.Ciudadano;

import java.util.ArrayList;

public class Evento {

    private final String nombre;
    public ArrayList<String> sintomasEnfermedad;

    public Evento(String nombre){  // Esta enfermedad cuenta con los siguientes sintomas
        this.nombre = nombre;            // fiebre, tos, dolor muscular, falta de aire. Si por lo menos hay 2 se considerara enfermo.
        sintomasEnfermedad = new ArrayList<>();

    }

    public void addSintomas(String sintoma){
        sintomasEnfermedad.add(sintoma);// fiebre, tos, dolor muscular, falta de aire. Si por lo menos hay 2 se considerara enfermo.
    }

    public String getNombre() {
        return nombre;
    }


}
