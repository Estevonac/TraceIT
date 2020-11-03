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
    // Opcion de 2 personas. Eliminarlo? El primer constructor funciona para ambos casos.
    public SolicitudEncuentro(Ciudadano emisor, Ciudadano unCiudadano, Fecha fechaDesde, Fecha fechaHasta, String zona){
        this.participanteUnico = unCiudadano;
        this.emisor = emisor;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.participantesConfirmados = new ArrayList<>();
        this.zona = zona;

    }

    // Primero le enviamos las solicitudes a los participantes
    public void enviarSolicitudes(){
        for (Ciudadano unCiudadano: participantes){
            unCiudadano.solicitudesRecibidas.add(this);
        }
        participantes.add(emisor); // anadimos al emisor a la lista de participantes para un manejo posterior mas facil
    }

    // Evaluamos la respuestas de los participantes. Despues de esta accion se puede proceder a crear el encuentro
    public void confirmarCiudadanos(){
        for (Ciudadano unCiudadano : participantes) {
            if (unCiudadano.getEstadoSolicitud(this)) {
                participantesConfirmados.add(unCiudadano);
            }
            else { //Por cada ciudadano que la tenga rechazada hay que actualizar el contador de solicitudes rechazadas del emisor
                this.emisor.solicitudesCounter ++;
            }
        }
    }

    public boolean getEstadoSolicitudParticipante(Ciudadano unCiudadano){ return unCiudadano.getEstadoSolicitud(this); }

    public ArrayList<Ciudadano> getParticipantes(){
        return  participantes;
    }

    public Fecha getFechaDesde(){ return fechaDesde; }

    public Fecha getFechaHasta() { return fechaHasta; }

    public ArrayList<Ciudadano> getParticipantesConfirmados() { return participantesConfirmados; }

    public String getZona() { return zona; }
}
