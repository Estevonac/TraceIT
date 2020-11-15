package Eventos;

import ABM.Ciudadano;
import Exceptions.InvalidDataException;
import Persistencia.GestorDeArchivos;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class BroteTest implements GestorDeArchivos {

    @Test
    public void constructorDeBroteDebeEscribirElArchivo() throws InvalidDataException, IOException { //Para el test uso valores escritos, pero el programa los obtiene de un encuentro
        ArrayList<Ciudadano> contagiados = new ArrayList<>();
        contagiados.add(new Ciudadano("1234611","11553344","Pilar"));
        contagiados.add(new Ciudadano("55124556","11857161","Escobar"));
        contagiados.add(new Ciudadano("1239611","11558004","Pilar"));
        contagiados.add(new Ciudadano("1934611","11903344","Pilar"));
        contagiados.add(new Ciudadano("1234886","11553331","Capital"));

        Brote broteEnfermedad = new Brote("Pilar",contagiados,contagiados.size(),"Covid");
        leerArchivo("Brotes"); //Funciona

    }
}