package Eventos;

import ABM.Ciudadano;
import Persistencia.Fecha;
import java.io.IOException;
import java.util.*;


public class Encuentro implements RastreadorEnfermos{

    private final ArrayList<Ciudadano> invitados;
    private final Fecha fechaDesde, fechaHasta;
    private final String zona;
    private HashMap<Enfermedad, Integer> enfermedadesYEnfermos;


    public Encuentro(ArrayList<Ciudadano> invitados, Fecha fechaDesde, Fecha fechaHasta, String zona){
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
            for (String sintomaCiudadano : unCiudadano.mostrarSintomas()) {
                if (!sintomasContacto.contains(sintomaCiudadano)) {
                    sintomasContacto.add(sintomaCiudadano);
                }
            }
            for (String sintoma : sintomasContacto) {//En un encuentro, una persona tiene un 60% de chance de contagiarse un sintoma de otro ciudadano
                //if ((Math.random() * 100) >= 1) { unCiudadano.presenciaSintomas(sintoma, getFechaHasta());}
                unCiudadano.presenciaSintomas(sintoma, getFechaHasta());
            }
            unCiudadano.evaluarSintomas();

            if (unCiudadano.estaEnfermo()){
                if (enfermedadesYEnfermos.containsKey(unCiudadano.getEnfermedadActual())) {
                    enfermedadesYEnfermos.put(unCiudadano.getEnfermedadActual(), enfermedadesYEnfermos.get(unCiudadano.getEnfermedadActual()) + 1);
                }
                else{
                    enfermedadesYEnfermos.put(unCiudadano.getEnfermedadActual(),1);
                }
            }
        }
        ordenarEnfermedadesYEnfermos(); //Testear metodo
        enfermedadesYEnfermos.clear();
        enfermedadesYEnfermos = getEnfermedadesParaBrotes();
        crearBrotesEnEncuentro(enfermedadesYEnfermos);
    }


    public ArrayList<Ciudadano> getEnfermosEnEncuentro(Enfermedad unaEnfermedad) throws IOException {
        ArrayList<Ciudadano> enfermos = new ArrayList<>();
        for (Ciudadano unCiudadano : invitados){
            unCiudadano.evaluarSintomas();
            if (unCiudadano.estaEnfermo()){
                enfermos.add(unCiudadano);
            }
        }
        return enfermos;
    }

    public HashMap<Enfermedad, Integer> getEnfermedadesParaBrotes(){
        HashMap<Enfermedad, Integer> hashMapParaBrotes = new HashMap<>();

        for ( Map.Entry<Enfermedad, Integer> entry : enfermedadesYEnfermos.entrySet()) {
            Enfermedad enfermedad = entry.getKey();
            Integer cantidad = entry.getValue();
           if (cantidad>=5){
               hashMapParaBrotes.put(enfermedad, cantidad);
           }
        }
        return hashMapParaBrotes;
    }

    public String getZona() { return zona; }

    public Fecha getFechaDesde() { return fechaDesde; }

    public Fecha getFechaHasta() { return fechaHasta; }

    public ArrayList<Ciudadano> getInvitados() {return invitados; }



    public int getCantidadMayorEnfermos(HashMap<String, Integer> hashASortear){ // necesito las enfermedades con mas de 5 o mas enfermos Rehacer el sort de Hashmap<String, Integer>
        Object[] toArray = hashASortear.entrySet().toArray();
        Arrays.sort(toArray, (Comparator) (o1, o2) -> ((Map.Entry<String, Integer>) o2).getValue()
                .compareTo(((Map.Entry<String, Integer>) o1).getValue()));
        return  hashASortear.values().stream().findFirst().get(); //Devuelve el valor mayor
    }

    public void ordenarEnfermedadesYEnfermos(){
        Object[] array = enfermedadesYEnfermos.entrySet().toArray();
        Arrays.sort(array, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
    }
    private void crearBrotesEnEncuentro(HashMap<Enfermedad, Integer> enfermedadesYEnfermos) throws IOException {
        for (Map.Entry<Enfermedad, Integer> entry : enfermedadesYEnfermos.entrySet()) {
            Enfermedad enfermedad = entry.getKey();
            Integer cantidad = entry.getValue();

            new Brote(getZona(),getEnfermosEnEncuentro(enfermedad),cantidad,enfermedad.getNombre());
        }
    }

}
