package Eventos;

import ABM.Ciudadano;

import java.util.ArrayList;

public class Enfermedad {

    private final String nombre;
    public ArrayList<String> sintomasEnfermedad;

    public Enfermedad(String nombre){  // Creamos una enfermedad y luego podemos anadirle los sintomas relacionados
        this.nombre = nombre;
        sintomasEnfermedad = new ArrayList<>();

    }

    public void addSintomas(String sintoma){
        sintomasEnfermedad.add(sintoma);// fiebre, tos, dolor muscular, falta de aire, fatiga, perdida de olfato, congestion. Si por lo menos hay 2 se considerara enfermo.
    }

    public String getNombre() {
        return nombre;
    }


}
