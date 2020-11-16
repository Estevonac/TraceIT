package Persistencia;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FechaTest {

    @Test
    public void getFechaAsStringDebeMostrarlaEnFormato() { // d/m h:min
        Fecha unaFecha = new Fecha(10,12,15,30);
       assertEquals(unaFecha.getFechaAsString(),"10/12 15:30");
    }

    @Test
    public void tiempoEntreFechasEnHoras() {
        Fecha unaFecha = new Fecha(29,11,12,0);
        Fecha otraFecha = new Fecha(30,11,12,0);

        assertEquals(unaFecha.tiempoEntreFechasEnHoras(otraFecha),24);
    }

    @Test
    public void traceITtoFecha() {
        String fecha = "07/09 12:30";

        int dia = Integer.parseInt(fecha.substring(0,2));
        int mes = Integer.parseInt(fecha.substring(3,5));
        int hora = Integer.parseInt(fecha.substring(6,8));
        int minuto = Integer.parseInt(fecha.substring(9));

        Fecha unaFecha = new Fecha(dia,mes,hora,minuto);
        //assertEquals(fecha,unaFecha.getFechaAsString());

    }

}