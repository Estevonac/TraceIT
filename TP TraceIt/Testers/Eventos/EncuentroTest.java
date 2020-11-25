package Eventos;

import ABM.Ciudadano;
import Exceptions.InvalidDataException;
import Persistencia.Fecha;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class EncuentroTest {

    @Test
    public void crearBrotesEnEncuentroDebeAgregarlosALaLista() throws IOException, InvalidDataException {
        ArrayList<Ciudadano> invitados = new ArrayList<>();
        invitados.add(new Ciudadano("1123124124","12445124","Pilar"));
        Encuentro unEncuentro = new Encuentro(invitados,new Fecha(10,12,11,30),new Fecha(10,12,13,25),"Pilar");
        HashMap<Enfermedad, Integer> enfermedadesEnEncuentro = new HashMap<>();
        enfermedadesEnEncuentro.put(new Enfermedad("Covid"),10);
        enfermedadesEnEncuentro.put(new Enfermedad("Fiebre"),8);

        unEncuentro.crearBrotesEnEncuentro(enfermedadesEnEncuentro);
        assertEquals(unEncuentro.listaBrotes.size(),2); //Las 2 enfermedades fueron anadidas a la lista de brotes

    }
    //Testeo grande
    @Test
    public void evaluarEncuentroDebeEnfermarALosParticipantes() throws InvalidDataException, IOException {
        int cantidadEnfermos = 0;
        ArrayList<String> sintomasGripe = new ArrayList<>();
        sintomasGripe.add("tos");
        sintomasGripe.add("estornudos");
        Enfermedad gripe = new Enfermedad("gripe",sintomasGripe);
        ArrayList<Ciudadano> invitados = new ArrayList<>();
        Ciudadano jorge = new Ciudadano("1172233","123412","Pilar");
        Ciudadano matias = new Ciudadano("11722331","123413","Pilar");
        Ciudadano juan = new Ciudadano("11722332","123414","Tortuguitas");
        Ciudadano juana = new Ciudadano("11722333","123415","CABA");
        Ciudadano maria = new Ciudadano("11722334","123416","Pilar");
        Ciudadano jose = new Ciudadano("117223346","12341643","CABA");

        jorge.presenciaSintomas("tos",new Fecha(10,12,11,30));
        matias.presenciaSintomas("estornudos",new Fecha(10,12,11,30));
        juan.presenciaSintomas("tos",new Fecha(10,12,11,30));
        juana.presenciaSintomas("estornudos",new Fecha(10,12,11,30));
        maria.presenciaSintomas("tos",new Fecha(10,12,11,30));
        jose.presenciaSintomas("estornudos",new Fecha(10,12,11,30));

        invitados.add(jorge);
        invitados.add(maria);
        invitados.add(matias);
        invitados.add(juan);
        invitados.add(juana);
        invitados.add(jose);

        Encuentro unEncuentro = new Encuentro(invitados,new Fecha(10,12,11,30),new Fecha(10,12,13,25),"Pilar");
        unEncuentro.evaluarEncuentro();


        for (Ciudadano unCiudadano : invitados){
            assertTrue(unCiudadano.estaEnfermo); //Todos deben estar enfermos
        }
        assertEquals(unEncuentro.listaBrotes.size(),1); // El brote de 6 personas por gripe en pilar
    }

}