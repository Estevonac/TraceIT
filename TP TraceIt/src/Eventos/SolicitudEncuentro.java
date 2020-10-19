package Eventos;

import ABM.Ciudadano;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class SolicitudEncuentro {

    final Ciudadano emisor;
    protected ArrayList<Ciudadano> participantesConfirmados;
    ArrayList<Ciudadano> participantes;
    final LocalDateTime fechaDesde;
    final LocalDateTime fechaHasta;
    public boolean estado;

    public SolicitudEncuentro(Ciudadano emisor,ArrayList<Ciudadano> participantes, LocalDateTime fechaDesde, LocalDateTime fechaHasta){
        this.participantes = participantes;
        this.emisor = emisor;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.participantesConfirmados = new ArrayList<>();

    }
    public ArrayList<Ciudadano> getParticipantes(){
        return  participantes;
    }

    public void confirmarCiudadanos(ArrayList<Ciudadano> participantes){
        for (Ciudadano unCiudadano : participantes) {
            if (unCiudadano.getEstadoSolicitud(this)) {
                participantesConfirmados.add(unCiudadano);
            }
        }
    }

    public void enviarSolicitudes(){ // Le envia a todos los participantes una solicitud que contiene nombre del emisor, participantes y fecha.

        for (Ciudadano unCiudadano: participantes){
            unCiudadano.solicitudesRecibidas.add(this);
        }
        participantes.add(emisor); // anadimos al emisor a la lista de participantes para un manejo posterior mas facil
    }

    public boolean getEstadoSolicitudParticipante(Ciudadano unCiudadano){
        return unCiudadano.getEstadoSolicitud(this);
    }

    public Ciudadano getEmisor() {
        return emisor;
    }

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }
}
