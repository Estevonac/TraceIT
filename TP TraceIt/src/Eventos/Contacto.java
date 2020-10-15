package Eventos;

import ABM.Ciudadano;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Contacto {
    public final boolean estanEnfermos;

    public Contacto(Ciudadano unCiudadano, Ciudadano otroCiudadano,String zona, LocalDateTime fechaDesde, LocalDateTime fechaHasta, boolean estanEnfermos){
// Una vez aceptado el encuentro aca se evalua el contacto (Extraer metodo de tuvoContacto)
        this.estanEnfermos = estanEnfermos;



    }
    public boolean getEstanEnfermos(){
        return estanEnfermos;
    }
}
