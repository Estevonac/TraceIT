package Eventos;

import ABM.Ciudadano;
import Persistencia.Fecha;

import java.io.IOException;
import java.util.*;

import static Eventos.Enfermedad.getEnfermedadesVigentes;

public class Encuentro {

    private final ArrayList<Ciudadano> invitados;
    private final Fecha fechaDesde, fechaHasta;
    private final String zona;
    private HashMap<Enfermedad, Integer> enfermedadesYEnfermos;


    public Encuentro(ArrayList<Ciudadano> invitados, Fecha fechaDesde, Fecha fechaHasta, String zona){
// Una vez aceptado el encuentro aca se evalua el contacto (Extraer metodo de tuvoContacto)
        this.invitados = invitados;
        this.zona = zona;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        enfermedadesYEnfermos = new HashMap<>();
    }

    public Encuentro crearEncuentro(SolicitudEncuentro unaSolicitud){
        return new Encuentro(unaSolicitud.getParticipantesConfirmados(),unaSolicitud.getFechaDesde(),unaSolicitud.getFechaHasta(),unaSolicitud.getZona());
    }

    //Metodo importante. Evalua el encuentro y distribuye la informacion que obtiene.
    public void evaluarEncuentro() throws IOException {
        ArrayList<String> sintomasContacto = new ArrayList<>();

        for (Ciudadano unCiudadano: this.getInvitados()) {
            for (String s : unCiudadano.mostrarSintomas()) {
                if (!sintomasContacto.contains(s)) {
                    if ((Math.random() * 100) >= 40) { //En un encuentro, una persona tiene un 60% de chance de contagiarse un sintoma de otro ciudadano
                        sintomasContacto.add(s);
                    }
                }
            }
                for (String sintoma : sintomasContacto){ // Anadimos los sintomas a cada ciudadano
                    unCiudadano.presenciaSintomas(sintoma,getFechaHasta()); //Utilizamos la fecha de finalizacion
            }
            for (Enfermedad unaEnfermedad : getEnfermedadesVigentes()) {
                unCiudadano.evaluarSintomas(unaEnfermedad); //Evaluamos los sintomas para cada enfermedad
            }

           if (unCiudadano.estaEnfermo()){
               enfermedadesYEnfermos.put(unCiudadano.getEnfermedadActual(),enfermedadesYEnfermos.get(unCiudadano.getEnfermedadActual()) +1);
           }

        }
        //if (cantEnfermosEnAlgunaEnfermedad >= 5){ Falta el ultimo paso. Chequear con todas las enfermedades y si hay mas de 5 enfermos crear el brote
           // Brote unBrote = new Brote(getZona(),enfermos,enfermos.size(),"Covid");
        }


    public int getCantEnfermosEnEncuentro(Enfermedad unaEnfermedad){
        int cantEnfermos = 0;
        for (Ciudadano unCiudadano : invitados){
            unCiudadano.evaluarSintomas(unaEnfermedad);
            if (unCiudadano.estaEnfermo()){
                cantEnfermos++;
            }
        }

        return cantEnfermos;
    }
    public ArrayList<Ciudadano> getEnfermosEnEncuentro(Enfermedad unaEnfermedad){
        ArrayList<Ciudadano> enfermos = new ArrayList<>();
        for (Ciudadano unCiudadano : invitados){
            unCiudadano.evaluarSintomas(unaEnfermedad);
            if (unCiudadano.estaEnfermo()){
                enfermos.add(unCiudadano);
            }
        }
        return enfermos;
    }

    public String getZona() { return zona; }

    public Fecha getFechaDesde() { return fechaDesde; }

    public Fecha getFechaHasta() { return fechaHasta; }

    public ArrayList<Ciudadano> getInvitados() {return invitados; }

    public int getCantidadMayorEnfermos(){ // necesito las enfermedades con mas de 5 o mas enfermos Rehacer el sort de Hashmap<String, Integer>
        Object[] a = enfermedadesYEnfermos.entrySet().toArray();
        Arrays.sort(a, (Comparator<Object>) (o1, o2) -> ((Map.Entry<String, Integer>) o2).getValue()
                .compareTo(((Map.Entry<String, Integer>) o1).getValue()));
        return -1;
    }
}
