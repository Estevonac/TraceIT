import ABM.Administrador;
import ABM.Ciudadano;
import Eventos.Enfermedad;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class TraceIT{ //Test del programa

    public static void main(String[] args) throws InvalidDataException {

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
        pedro.presenciaSintomas("tos",LocalDateTime.of(2020,10,9,12,30));
        pedro.presenciaSintomas("dolor muscular",LocalDateTime.of(2020,10,9,12,30));
        julian.presenciaSintomas("fiebre",LocalDateTime.of(2020,10,9,12,30));
        julian.presenciaSintomas("tos",LocalDateTime.of(2020,10,9,12,30));

        pedro.mostrarSintomas();
        julian.mostrarSintomas();

        System.out.println("Pedro esta enfermo?: " + pedro.estaEnfermo);
        System.out.println("Julian esta enfermo?: " + julian.estaEnfermo);

// Generamos un contacto entre 2 ciudadanos, ahi tambien reevaluamos el estado de enfermedad y mostramos los nuevos sintomas
        try {
            pedro.tieneContacto(participantes,LocalDateTime.of(2020,10,9,12,30),LocalDateTime.of(2020,10,9,14,30),covid); //El metodo no esta completo y no funciona adecuadamente todavia
        } catch (InexistentUserException e) {
            e.printStackTrace();
        }

        pedro.mostrarSintomas();
        julian.mostrarSintomas();

//Imprimimos si los ciudadanos contrayeron la enfermedad despues del contacto
        System.out.println("Pedro esta enfermo?: " + pedro.estaEnfermo);
        System.out.println("Julian esta enfermo?: " + julian.estaEnfermo);


    }
}
