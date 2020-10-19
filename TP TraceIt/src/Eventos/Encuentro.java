package Eventos;

import ABM.Ciudadano;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Encuentro {


    private final ArrayList<Ciudadano> participantes;
    private final LocalDateTime fechaDesde, fechaHasta;
    private final String zona;

    public Encuentro(ArrayList<Ciudadano> participantes, String zona, LocalDateTime fechaDesde, LocalDateTime fechaHasta){
// Una vez aceptado el encuentro aca se evalua el contacto (Extraer metodo de tuvoContacto)

        this.participantes = participantes;
        this.zona = zona;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;

    }

    public ArrayList<Ciudadano> getParticipantes() {
        return participantes;
    }

    public String getZona() {
        return zona;
    }

    public void evaluarEncuentro(ArrayList<Ciudadano> participantes){

        for(Ciudadano unCiudadano : participantes){
           // unCiudadano.evaluarSintomas(); Tendria que evaluar el encuentro si ocurrio algo?
        }
    }
}
