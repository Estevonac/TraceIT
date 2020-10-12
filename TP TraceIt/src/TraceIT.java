import ABM.Administrador;
import ABM.Ciudadano;
import Eventos.Evento;
import java.time.LocalDateTime;


public class TraceIT {

    public static void main(String[] args) {
//Creamos una enfermedad y le pasamos los sintomas de esta
        Evento covid = new Evento("Covid");
        covid.addSintomas("tos");
        covid.addSintomas("dolor muscular");
        covid.addSintomas("fiebre");
        covid.addSintomas("falta de aire");

//Creamos usuarios
        Administrador juan = new Administrador("Juan", "admin123");
        Ciudadano pedro = new Ciudadano("201234560",11223344);
        Ciudadano julian = new Ciudadano("2044112250", 55667788);

//Registramos los sintomas de cada usuario y mostramos el estado de si presentan la enfermedad antes creada
        pedro.presenciaSintomas("tos");
        pedro.presenciaSintomas("dolor muscular");
        julian.presenciaSintomas("fiebre");
        julian.presenciaSintomas("tos");

        pedro.mostrarSintomas();
        julian.mostrarSintomas();

        System.out.println("Pedro esta enfermo?: " + pedro.estaEnfermo);
        System.out.println("Julian esta enfermo?: " + julian.estaEnfermo);

// Generamos un contacto entre 2 ciudadanos, ahi tambien reevaluamos el estado de enfermedad y mostramos los nuevos sintomas
        pedro.tuvoContacto(julian,LocalDateTime.of(2020,10,9,12,30),LocalDateTime.of(2020,10,9,14,30), "Pilar",covid); //El metodo no esta completo y no funciona adecuadamente todavia

        pedro.mostrarSintomas();
        julian.mostrarSintomas();

//Imprimimos si los ciudadanos contrayeron la enfermedad despues del contacto
        System.out.println("Pedro esta enfermo?: " + pedro.estaEnfermo);
        System.out.println("Julian esta enfermo?: " + julian.estaEnfermo);


    }
}
