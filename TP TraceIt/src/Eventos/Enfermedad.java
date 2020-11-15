package Eventos;

import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Enfermedad implements GestorDeArchivos{


    //Probar de implementar distintos tipos de sintomas. Sintomas contagiables y no contagiables.

    private final String nombre;
    public ArrayList<String> sintomasEnfermedad;
    public ArrayList<Enfermedad> enfermedadesVigentes = new ArrayList<>();

    public Enfermedad(String nombre) throws IOException {  // Creamos una enfermedad y luego podemos anadirle los sintomas relacionados
        this.nombre = nombre;
        sintomasEnfermedad = new ArrayList<>();
        enfermedadesVigentes.add(this);
        HashMap<String, ArrayList<String>> enfermedadesVigentes = new HashMap<>(); // Usado para la persistencia


    }

    public Enfermedad(String nombre,ArrayList<String> sintomas) throws IOException {  // Creamos una enfermedad y luego podemos anadirle los sintomas relacionados
        this.nombre = nombre;
        sintomasEnfermedad = sintomas;
        enfermedadesVigentes.add(this);
        HashMap<String, ArrayList<String>> enfermedadesVigentes = new HashMap<>(); // Usado para la persistencia
    }

    public void addSintomas(String sintoma) throws IOException { //Borra los sintomas y solo anade uno
        sintomasEnfermedad.add(sintoma);// fiebre, tos, dolor muscular, falta de aire, fatiga, perdida de olfato, congestion.
        editarArchivo("Enfermedades",getNombre(),getNombre() + "," + sintoma);
    }
    @Override
    public String toString(){
        StringBuilder sintomasEnString = new StringBuilder();
        for (int i = 0; i < sintomasEnfermedad.size(); i++) {
            sintomasEnString.append(sintomasEnfermedad.get(i)).append(",");
            if (i == (sintomasEnfermedad.size()-1)){
                sintomasEnString.deleteCharAt(sintomasEnString.length());
            }
        }
        return sintomasEnString.toString();
    }


    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> mostrarSintomas(){
        return sintomasEnfermedad;
    }
}
