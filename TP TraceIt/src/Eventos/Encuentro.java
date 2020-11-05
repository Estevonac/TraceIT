package Eventos;

import ABM.Ciudadano;
import Persistencia.Fecha;
import java.io.IOException;
import java.util.*;

//import static Evento.Enfermedad.getEnfermedadesVigentes;

public class Encuentro implements RastreadorEnfermos{

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
                    sintomasContacto.add(s);

                }
            }
            for (String sintoma : sintomasContacto) {//En un encuentro, una persona tiene un 60% de chance de contagiarse un sintoma de otro ciudadano
                if ((Math.random() * 100) >= 40) {
                    unCiudadano.presenciaSintomas(sintoma, getFechaHasta()); //Utilizamos la fecha de finalizacion
                }
            }
            unCiudadano.evaluarSintomas();

            if (unCiudadano.estaEnfermo()){
                enfermedadesYEnfermos.put(unCiudadano.getEnfermedadActual(),enfermedadesYEnfermos.get(unCiudadano.getEnfermedadActual()) +1);
            }

        }
        ordenarEnfermedadesYEnfermos(); //Testear metodo
        enfermedadesYEnfermos.entrySet().forEach((entry) ->
        {if(entry.getValue()>=5){
            try {
                new Brote(getZona(),getInvitados(),getCantEnfermosEnEncuentro(entry.getKey()),entry.getKey().getNombre());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        });
        //if (cantEnfermosEnAlgunaEnfermedad >= 5){ Falta el ultimo paso. Chequear con todas las enfermedades y si hay mas de 5 enfermos crear el brote
        //  new Brote(getZona(),enfermos,enfermos.size(),getEnfermedad());
    }


    public int getCantEnfermosEnEncuentro(Enfermedad unaEnfermedad) throws IOException {
        int cantEnfermos = 0;
        for (Ciudadano unCiudadano : invitados){
            unCiudadano.evaluarSintomas();
            if (unCiudadano.estaEnfermo()){
                cantEnfermos++;
            }
        }

        return cantEnfermos;
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

}
