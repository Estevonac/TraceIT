package Eventos;

import ABM.Ciudadano;

import java.util.ArrayList;
import java.util.Date;

public class Contacto {
    public final boolean estanEnfermos;

    public Contacto(Ciudadano unCiudadano, String zona, Date fechaDesde, Date fechaHasta, boolean estanEnfermos){

        this.estanEnfermos = estanEnfermos;



    }
    public boolean getEstanEnfermos(){
        return estanEnfermos;
    }
}
