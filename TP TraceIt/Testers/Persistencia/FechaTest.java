package Persistencia;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FechaTest {

    @Test
    public void getFechaAsStringDebeMostrarlaEnFormato() { // d/m h:min
        Fecha unaFecha = new Fecha(10,12,15,30);
       assertEquals(unaFecha.getFechaAsString(),"10/12 15:30");
    }

    @Test
    public void tiempoEntreFechasEnHoras() {
        Fecha unaFecha = new Fecha(30,10,12,0);
        Fecha otraFecha = new Fecha(1,11,12,0);

        // Fecha unaFecha = new Fecha(20,10,13,30);
        //Fecha otraFecha = new Fecha(20,10,16,30);

        assertEquals(unaFecha.tiempoEntreFechasEnHoras(otraFecha),24);
    }

    @Test
    public void traceITtoFecha() {
        String fecha = "7/9 12:30";
        int dia = Integer.parseInt(Arrays.toString(fecha.split("/")));
        int mes = Integer.parseInt(fecha.substring(fecha.indexOf("/"), fecha.indexOf(" ")));
        int hora = Integer.parseInt(fecha.substring(fecha.indexOf(" ", fecha.indexOf(":"))));
        int minuto = Integer.parseInt(fecha.substring(fecha.indexOf(":", fecha.length())));

        Fecha unaFecha = new Fecha(dia,mes,hora,minuto);
        assertEquals(unaFecha,fecha);
    }

}