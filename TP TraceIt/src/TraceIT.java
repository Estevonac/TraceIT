import ABM.Administrador;
import ABM.Ciudadano;
import Eventos.Enfermedad;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Persistencia.Fecha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TraceIT{ //Test del programa

    public static void main(String[] args) throws InvalidDataException, IOException {

//Creamos una enfermedad y le pasamos los sintomas de esta
        Enfermedad covid = new Enfermedad("Covid");
        covid.addSintomas("tos");
        covid.addSintomas("dolor muscular");
        covid.addSintomas("fiebre");
        covid.addSintomas("falta de aire");

//Creamos usuarios
        ArrayList<Ciudadano> participantes = new ArrayList<>();
        Administrador juan = new Administrador("Juan", "admin123");
        Ciudadano pedro = new Ciudadano("201234560","11223344", "Pilar");
        Ciudadano julian = new Ciudadano("2044112250", "55667788", "CABA");
        participantes.add(julian);


//Registramos los sintomas de cada usuario y mostramos el estado de si presentan la enfermedad antes creada
        pedro.presenciaSintomas("tos", new Fecha(10,10,9,12));
        pedro.presenciaSintomas("dolor muscular",new Fecha(10,10,12,30));
        julian.presenciaSintomas("fiebre",new Fecha(10,9,12,30));
        julian.presenciaSintomas("tos",new Fecha(10,10,12,30));

        pedro.mostrarSintomas();
        julian.mostrarSintomas();

        System.out.println("Pedro esta enfermo?: " + pedro.estaEnfermo);
        System.out.println("Julian esta enfermo?: " + julian.estaEnfermo);

// Generamos un contacto entre 2 ciudadanos, ahi tambien reevaluamos el estado de enfermedad y mostramos los nuevos sintomas
        try {
            pedro.solicitarContacto(participantes,new Fecha(10,10,9,12),new Fecha(10,9,14,30),"Pilar"); //El metodo no esta completo y no funciona adecuadamente todavia
        } catch (InexistentUserException e) {
            e.printStackTrace();
        }

        pedro.mostrarSintomas();
        julian.mostrarSintomas();

//Imprimimos si los ciudadanos contrayeron la enfermedad despues del contacto
        System.out.println("Pedro esta enfermo?: " + pedro.estaEnfermo);
        System.out.println("Julian esta enfermo?: " + julian.estaEnfermo);


    }


    private static void cargarArchivos() throws IOException { //Lee los datasets y los carga en el programa
        String[] datasets = {"Administradores", "Brotes", "AnsesCiudadanos", "Brotes", "Encuentros", "Enfermedades"};

        for (String archivo : datasets) {

            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String head = bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dato = line.split(",");
                new Administrador(dato[0],dato[1]);
            }
            bufferedReader.close();

        }
    }


}

