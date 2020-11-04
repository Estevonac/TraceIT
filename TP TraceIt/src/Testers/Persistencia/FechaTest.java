package Persistencia;

import org.junit.Test;

import static org.junit.Assert.*;

public class FechaTest {

    @Test
    public void getFechaAsStringDebeMostrarlaEnFormato() { // d/m h:min
        Fecha unaFecha = new Fecha(10,12,15,30);
       assertEquals(unaFecha.getFechaAsString(),"10/12 15:30");
    }

    @Test
    public void tiempoEntreFechasEnHoras() {
    }
}