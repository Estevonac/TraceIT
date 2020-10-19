package ABM;

import Eventos.Encuentro;
import Eventos.Enfermedad;
import Eventos.SolicitudEncuentro;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ciudadano{

    public boolean habilitado;
    public boolean estaEnfermo;
    int solicitudesCounter;
    final String numeroTelefono;
    private final String cuil;
    ArrayList<String> sintomas;
    public ArrayList<SolicitudEncuentro> solicitudesRecibidas;
    private final String zona;



    public Ciudadano(String cuil, String numeroTelefono,String zona) throws InvalidDataException {
            this.cuil = cuil;
            this.numeroTelefono = numeroTelefono;
            habilitado = true;
            solicitudesCounter = 0;
            estaEnfermo = false;
            sintomas = new ArrayList<>();
            solicitudesRecibidas = new ArrayList<>();
            this.zona = zona;
        }


        public String getNumeroTelefono() {
            return numeroTelefono;
        }

        public String getCuil() {
            return cuil;
        }

        public boolean getHabilitado() {
            return habilitado;
        }



    public boolean isEstaEnfermo(){ // Evalua si el ciudadano esta enfermo.
        return estaEnfermo;
    }

    public void evaluarSintomas(Enfermedad unaEnfermedad){ // Metodo para evaluar si un ciudadano esta enfermo.
        int sintomasCompartidos = 0;
        for (String sintomaEnfermedad: unaEnfermedad.sintomasEnfermedad) {
            for (String sintomaCiudadano: sintomas) {

                if (sintomaEnfermedad.equals(sintomaCiudadano)){
                    sintomasCompartidos++;
                }
            }
        }
        if (sintomasCompartidos >= 2){
            estaEnfermo = true;
            habilitado = false; // Si un ciudadano ahora esta enfermo, entonces no puede solicitar contactos ya que debe aislarse
        }                       // Por lo tanto este es bloqueado.
        else{
            estaEnfermo = false;
        }

    }

    public void tieneContacto(ArrayList<Ciudadano> participantes, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Enfermedad unEnfermedad) throws InexistentUserException { // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.

        SolicitudEncuentro unEncuentro = new SolicitudEncuentro(this,participantes,fechaDesde,fechaHasta); // Reestructurar y extraer metodo en clase SolicitudEncuentro
            if (unEncuentro.estado = true) {     //Evaluar el estado de la solicitud y pasarselo a Encuentro para evaluar el metodo
                ArrayList<String> sintomasContacto = this.sintomas;
                for (Ciudadano unCiudadano: participantes) {


                for (String s : unCiudadano.sintomas) { // Arreglar para que funcione con multiples ciudadanos
                    if (!sintomasContacto.contains(s)) {
                        if ((Math.random() * 100) >= 45) { //En un encuentro, una persona tiene un 55% de chance de contagiarse un sintoma de otro ciudadano
                            sintomasContacto.add(s);
                        }
                    }
                }
                //Anado los sintomas del contacto a ambos ciudadanos
                this.sintomas = sintomasContacto;
                unCiudadano.sintomas = sintomasContacto;
                System.out.println("Sintomas despues del contagio:" + sintomasContacto);

                // Ahora necesito que se reevalue si los ciudadanos estan enfermos.
                this.estaEnfermo = this.isEstaEnfermo();
                unCiudadano.estaEnfermo = unCiudadano.isEstaEnfermo();

                /*if ((!unCiudadano.estaEnfermo) && (!this.estaEnfermo)) { // Crear encuentros
                    Encuentro encuentro = new Encuentro(unCiudadano, this, zona, fechaDesde, fechaHasta);

                } else {
                    Encuentro encuentro = new Encuentro(unCiudadano, this, zona, fechaDesde, fechaHasta);
                    solicitudesCounter++;
                }*/
                }
            }
            unEncuentro = null; // Eliminar la solicitud creada antes. Preferentenmente
    }

    public boolean evaluarContacto(String respuesta, SolicitudEncuentro unEncuentro){ //Como hacemos para que se llame cuando un ciudadano dice
                                                                                        //tener un encuentro con otro
        unEncuentro.estado = respuesta.equals("true");


        return unEncuentro.estado;

    }



    public void presenciaSintomas(String unSintoma, LocalDateTime fechaSintoma){  // Anade sintomas a un ciudadano Necesitamos fecha??
            sintomas.add(unSintoma);


    }

    private void eliminarSintoma(String unSintoma, LocalDateTime fechaFin){ // Elimina sintomas a un ciudadano
            sintomas.remove(unSintoma);
    }

    public ArrayList<String> mostrarSintomas(){
            return sintomas;
    }

    public void aceptarSolicitud(SolicitudEncuentro unaSolicitud) throws InvalidDataException {//Respuesta a una solicitud

        if (solicitudesRecibidas.size()>0){

        for(SolicitudEncuentro solicitud : solicitudesRecibidas) { // loopear y buscar solicitud sino exception
            if (solicitud.equals(unaSolicitud)){
                solicitud.estado = true;
            }
        }
    }
    else{
        throw new InvalidDataException("No hay ninguna solicitud de encuentro");

    }
    }
    public void rechazarSolicitud(SolicitudEncuentro unaSolicitud) throws InvalidDataException {//Respuesta a una solicitud

        if (solicitudesRecibidas.size()>0){

            for(SolicitudEncuentro solicitud : solicitudesRecibidas) { // loopear y buscar solicitud sino exception
                if (solicitud.equals(unaSolicitud)){
                    solicitud.estado = false;
                }
            } // Hay que ver que cuando aceptamos o rechazamos las solicitudes estas tienen que desaparecer de la lista de solicitudes
        }
        else{
            throw new InvalidDataException("No hay ninguna solicitud de encuentro");

        }
    }

    public ArrayList<SolicitudEncuentro> mostrarSolicitudes(){
        return solicitudesRecibidas;
    }

    public boolean getEstadoSolicitud(SolicitudEncuentro unaSolicitud){
        return unaSolicitud.estado;
    }
}


