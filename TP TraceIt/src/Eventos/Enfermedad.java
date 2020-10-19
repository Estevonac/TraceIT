package Eventos;

import java.util.ArrayList;

public class Enfermedad {

    /*
    Probar de implementar distintos tipos de sintomas. Sintomas contagiables y no contagiables.
     */
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

    public ArrayList<String> mostrarSintomas(){
        return sintomasEnfermedad;
    }
}
