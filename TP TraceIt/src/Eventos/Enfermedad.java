package Eventos;

import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Enfermedad implements GestorDeArchivos{

    /*
    Probar de implementar distintos tipos de sintomas. Sintomas contagiables y no contagiables.
     */
    private final String nombre;
    public ArrayList<String> sintomasEnfermedad;
    public ArrayList<Enfermedad> enfermedadesVigentes = new ArrayList<>();

    public Enfermedad(String nombre) throws IOException {  // Creamos una enfermedad y luego podemos anadirle los sintomas relacionados
        this.nombre = nombre;
        sintomasEnfermedad = new ArrayList<>();
        enfermedadesVigentes.add(this);
        HashMap<String, ArrayList<String>> enfermedadesVigentes = new HashMap<>(); // Usado para la persistencia
        escribirArchivo("Enfermedades",nombre); // La escribo en el archivo de forma. Enfermedad, Sintomas relacionados

    }

    public Enfermedad(String nombre,ArrayList<String> sintomas) throws IOException {  // Creamos una enfermedad y luego podemos anadirle los sintomas relacionados
        this.nombre = nombre;
        sintomasEnfermedad = sintomas;
        enfermedadesVigentes.add(this);
        HashMap<String, ArrayList<String>> enfermedadesVigentes = new HashMap<>(); // Usado para la persistencia
        escribirArchivo("Enfermedades",nombre + "," + sintomasEnfermedad); // La escribo en el archivo de forma. Enfermedad, Sintomas relacionados

    }

    public void addSintomas(String sintoma) throws IOException {
        sintomasEnfermedad.add(sintoma);// fiebre, tos, dolor muscular, falta de aire, fatiga, perdida de olfato, congestion. Si por lo menos hay 2 se considerara enfermo.
        editarArchivo("Enfermedades",getNombre(),getNombre() + "," + sintoma);
    }



    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> mostrarSintomas(){
        return sintomasEnfermedad;
    }
}
