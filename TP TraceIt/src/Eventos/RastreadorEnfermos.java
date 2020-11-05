package Eventos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface RastreadorEnfermos {

    // Extraer metodos aca. Este sera como un administrador fantasma que se va a encargar de reportar los enfermos y veer
    // que pasa en los encuentros. Quienes se enferman a quienes se les dan sintomas y a quienes no.

//Es necesario??

    default ArrayList<Enfermedad> getEnfermedadesVigentes() throws IOException { //Lo puedo extraer a la interfaz?
        ArrayList<Enfermedad> enfermedadesVigentes = new ArrayList<>();
        FileReader fileReader = new FileReader("Enfermedades");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) { //Verificar y testear
            String[] dato = line.split(",");
            enfermedadesVigentes.add(new Enfermedad(dato[0]));
        }
        bufferedReader.close();

        return enfermedadesVigentes;
    }
}
