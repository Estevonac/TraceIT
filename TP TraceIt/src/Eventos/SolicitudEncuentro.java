package Eventos;

import ABM.Ciudadano;
import Persistencia.Fecha;
import java.util.ArrayList;

public class SolicitudEncuentro {

    final Ciudadano emisor;
    protected ArrayList<Ciudadano> participantesConfirmados;
    ArrayList<Ciudadano> participantes;
    final Fecha fechaDesde;
    final Fecha fechaHasta;
    public boolean estado;
    private final String zona;
    Ciudadano participanteUnico;

    public SolicitudEncuentro(Ciudadano emisor, ArrayList<Ciudadano> participantes, Fecha fechaDesde, Fecha fechaHasta, String zona){
        this.participantes = participantes;
        this.emisor = emisor;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.participantesConfirmados = new ArrayList<>();
        this.zona = zona;

    }
    public SolicitudEncuentro(Ciudadano emisor, Ciudadano unCiudadano, Fecha fechaDesde, Fecha fechaHasta, String zona){
        this.participanteUnico = unCiudadano;
        this.emisor = emisor;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.participantesConfirmados = new ArrayList<>();
        this.zona = zona;

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

    public Fecha getFechaDesde() {
        return fechaDesde;
    }

    public Fecha getFechaHasta() {
        return fechaHasta;
    }

    public ArrayList<Ciudadano> getParticipantesConfirmados() {
        return participantesConfirmados;
    }

    public String getZona() {
        return zona;
    }
}
