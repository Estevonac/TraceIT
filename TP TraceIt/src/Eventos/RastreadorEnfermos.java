package Eventos;

import ABM.Ciudadano;

import java.io.IOException;
import java.util.ArrayList;

public interface RastreadorEnfermos {
//Es necesario??

    default ArrayList<Enfermedad> getEnfermedadesVigentes() throws IOException {
        return Enfermedad.enfermedadesVigentes;
        /*ArrayList<Enfermedad> enfermedadesVigentes = new ArrayList<>();
        FileReader fileReader = new FileReader("TP TraceIT/src/Datasets/Enfermedades.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) { //Me esta leyendo todas las lineas
            String[] dato = line.split(",");

            dato[1] = dato[1].replaceAll("[^a-zA-Z ]", "");
            dato[dato.length-1] = dato[dato.length-1].replaceAll("[^a-zA-Z ]", "");

            ArrayList<String> sintomasDeEnfermedad = new ArrayList<>(Arrays.asList(dato).subList(1, dato.length));
            System.out.println(sintomasDeEnfermedad);

            enfermedadesVigentes.add(new Enfermedad(dato[0], sintomasDeEnfermedad));
            //sintomasDeEnfermedad.clear(); //Reseteamos la lista de sintomas
        }
        bufferedReader.close();
        return enfermedadesVigentes;
        */

    }

     default void avisoACiudadano(Ciudadano emisor, Ciudadano receptor){
        receptor.notificacion = "Tuviste un contacto reciente con: " + emisor.getCuil() + " y ahora esta enfermo.";

     }
}
