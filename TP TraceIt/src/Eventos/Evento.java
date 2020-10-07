package Eventos;

import ABM.Ciudadano;

import java.util.ArrayList;

public class Evento {

    ArrayList<String> sintomasEnfermedad;

    public Evento(){  // Esta enfermedad cuenta con los siguientes sintomas
                      // fiebre, tos, dolor muscular, falta de aire. Si por lo menos hay 2 se considerara enfermo.
        sintomasEnfermedad = new ArrayList<>();

    }

    public void addSintomas(String sintoma){
        sintomasEnfermedad.add(sintoma);// fiebre, tos, dolor muscular, falta de aire. Si por lo menos hay 2 se considerara enfermo.
    }

    public void estaEnfermo(Ciudadano unCiudadano){
        int sintomasCounter = 0;
        for (String s: unCiudadano.mostrarSintomas()) {
            for (String e: sintomasEnfermedad) {
                if (s.equals(e)){
                    sintomasCounter++;
                }
            }

        }
        unCiudadano.estaEnfermo = sintomasCounter >= 2;
    }
}
