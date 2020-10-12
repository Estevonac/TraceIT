package Eventos;

import ABM.Ciudadano;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Contacto {
    public final boolean estanEnfermos;

    public Contacto(Ciudadano unCiudadano, String zona, LocalDateTime fechaDesde, LocalDateTime fechaHasta, boolean estanEnfermos){

        this.estanEnfermos = estanEnfermos;



    }
    public boolean getEstanEnfermos(){
        return estanEnfermos;
    }
}
