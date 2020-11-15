package Eventos;

import ABM.Ciudadano;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface RastreadorEnfermos {
//Es necesario??

    default ArrayList<Enfermedad> getEnfermedadesVigentes() throws IOException {
        ArrayList<Enfermedad> enfermedadesVigentes = new ArrayList<>();
        FileReader fileReader = new FileReader("TP TraceIT/src/Datasets/Enfermedades.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) { //Verificar y testear
            String[] dato = line.split(",");
            ArrayList<String> sintomasDeEnfermedad = new ArrayList<>();
            for (int i = 1; i < dato.length; i++) {
                sintomasDeEnfermedad.add(dato[i]);
            }
            enfermedadesVigentes.add(new Enfermedad(dato[0], sintomasDeEnfermedad));
            sintomasDeEnfermedad.clear(); //Reseteamos la lista de sintomas
        }
        bufferedReader.close();

        return enfermedadesVigentes;
    }

     default void avisoACiudadano(Ciudadano emisor, Ciudadano receptor){
        receptor.notificacion = "Tuviste un contacto reciente con: " + emisor.getCuil() + " y ahora esta enfermo.";

     }
}
