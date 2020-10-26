package ABM;

import Eventos.Enfermedad;
import Eventos.SolicitudEncuentro;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Persistencia.Fecha;
import java.util.ArrayList;

public class Ciudadano{

    public boolean habilitado;
    public boolean estaEnfermo;
    int solicitudesCounter;
    final String numeroTelefono;
    private final String cuil;
    ArrayList<String> sintomas;
    public ArrayList<SolicitudEncuentro> solicitudesRecibidas, proximosEncuentros;
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



    public boolean estaEnfermo(){ // Evalua si el ciudadano esta enfermo.
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

    public void solicitarContacto(ArrayList<Ciudadano> participantes, Fecha fechaDesde, Fecha fechaHasta,String zona) throws InexistentUserException { // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.

        SolicitudEncuentro unEncuentro = new SolicitudEncuentro(this,participantes,fechaDesde,fechaHasta,zona); // Reestructurar y extraer metodo en clase SolicitudEncuentro
    unEncuentro.enviarSolicitudes();
    }
    public void solicitarContacto(Ciudadano unCiudadano, Fecha fechaDesde, Fecha fechaHasta,String zona) throws InexistentUserException { // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.

        SolicitudEncuentro unEncuentro = new SolicitudEncuentro(this,unCiudadano,fechaDesde,fechaHasta,zona); // Reestructurar y extraer metodo en clase SolicitudEncuentro

    }

    public boolean evaluarContacto(SolicitudEncuentro unEncuentro){ //Como hacemos para que se llame cuando un ciudadano dice
                                                                                        //tener un encuentro con otro
        if (unEncuentro.estado = true) {     //Evaluar el estado de la solicitud y pasarselo a Encuentro para evaluar el metodo
            ArrayList<String> sintomasContacto = this.sintomas;
            for (Ciudadano unCiudadano: unEncuentro.getParticipantesConfirmados()) {


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
                this.estaEnfermo = this.estaEnfermo();
                unCiudadano.estaEnfermo = unCiudadano.estaEnfermo();

                /*if ((!unCiudadano.estaEnfermo) && (!this.estaEnfermo)) { // Crear encuentros
                    Encuentro encuentro = new Encuentro(unCiudadano, this, zona, fechaDesde, fechaHasta);

                } else {
                    Encuentro encuentro = new Encuentro(unCiudadano, this, zona, fechaDesde, fechaHasta);
                    solicitudesCounter++;
                }*/
            }
        }
        unEncuentro = null; // Eliminar la solicitud creada antes. Preferentenmente
        return  true;
    }



    public void presenciaSintomas(String unSintoma, Fecha fechaSintoma){  // Anade sintomas a un ciudadano Necesitamos fecha??
            sintomas.add(unSintoma); // que pasa si hay un sintoma duplicado. Conservar el mas reciente.


    }

    private void eliminarSintoma(String unSintoma, Fecha fechaFin){ // Elimina sintomas a un ciudadano
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
                solicitudesRecibidas.remove(solicitud); // La elimino de mi lista de solicitudes
                proximosEncuentros.add(solicitud);
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
                    solicitud.getParticipantes().remove(this); // Lo sacamos a este ciudadano de la lista de participantes.
                    solicitudesRecibidas.remove(solicitud);
                }
            }
        }
        else{
            throw new InvalidDataException("No hay ninguna solicitud de encuentro");

        }
    }

    public ArrayList<SolicitudEncuentro> mostrarSolicitudesRecibidas(){
        return solicitudesRecibidas;
    }

    public ArrayList<SolicitudEncuentro> mostrarEventosProximos(){return proximosEncuentros;}

    public boolean getEstadoSolicitud(SolicitudEncuentro unaSolicitud){
        return unaSolicitud.estado;
    }

}


