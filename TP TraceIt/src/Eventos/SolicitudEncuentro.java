package Eventos;

import ABM.Ciudadano;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SolicitudEncuentro {

    final Ciudadano emisor, receptor;

    final LocalDateTime fechaDesde;
    final LocalDateTime fechaHasta;
    public boolean estado;

    public SolicitudEncuentro(Ciudadano emisor,Ciudadano receptor, LocalDateTime fechaDesde, LocalDateTime fechaHasta){
        this.receptor = receptor;
        this.emisor = emisor;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;

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
