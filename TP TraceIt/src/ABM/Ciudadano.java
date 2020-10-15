package ABM;

import Eventos.Contacto;
import Eventos.Enfermedad;
import Eventos.SolicitudEncuentro;
import Exceptions.InexistentUserException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Ciudadano{

    public boolean habilitado;
    public boolean estaEnfermo;
    int solicitudesCounter;
    final String numeroTelefono;
    private final String cuil;
    ArrayList<String> sintomas;
    ArrayList<Ciudadano> contactos;
    private final String zona;


        public Ciudadano(String cuil, String numeroTelefono,String zona) {
            this.cuil = cuil;
            this.numeroTelefono = numeroTelefono;
            habilitado = true;
            solicitudesCounter = 0;
            estaEnfermo = false;
            sintomas = new ArrayList<>();
            contactos = new ArrayList<>();
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


        public boolean isEstaEnfermo(Enfermedad unEnfermedad){ // Evalua si el ciudadano esta enfermo.

            int sintomasCounter = 0;
            for (String s: this.mostrarSintomas()) {
                for (String e: unEnfermedad.sintomasEnfermedad) {
                    if (s.equals(e)){
                        sintomasCounter++;
                    }
                }

            }
            return this.estaEnfermo = sintomasCounter >= 2;
        }

    public void tuvoContacto(Ciudadano unCiudadano, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Enfermedad unEnfermedad) throws InexistentUserException { // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.

        SolicitudEncuentro unEncuentro = new SolicitudEncuentro(this,unCiudadano,fechaDesde,fechaHasta); // Reestructurar y extraer metodo en clase SolicitudEncuentro
            if (unEncuentro.estado = true) {     //Evaluar el estado de la solicitud y pasarselo a Contacto para evaluar el metodo
                ArrayList<String> sintomasContacto = this.sintomas;

                for (String s : unCiudadano.sintomas) {
                    if (!sintomasContacto.contains(s)) {
                        sintomasContacto.add(s);
                    }
                }
                //Anado los sintomas del contacto a ambos ciudadanos
                this.sintomas = sintomasContacto;
                unCiudadano.sintomas = sintomasContacto;
                System.out.println("Sintomas despues del contagio:" + sintomasContacto);

                // Ahora necesito que se reevalue si los ciudadanos estan enfermos.
                this.estaEnfermo = this.isEstaEnfermo(unEnfermedad);
                unCiudadano.estaEnfermo = unCiudadano.isEstaEnfermo(unEnfermedad);

                if ((!unCiudadano.estaEnfermo) && (!this.estaEnfermo)) {
                    Contacto contacto = new Contacto(unCiudadano, this, zona, fechaDesde, fechaHasta, false);

                } else {
                    Contacto contacto = new Contacto(unCiudadano, this, zona, fechaDesde, fechaHasta, true);
                }
            }

                 /*if (){   // Si la respuesta es no. Contador ++. if contador = 5 usuario bloqeuado

  // Podemos tener un boolean que lo recibe unCiudadano y tiene que decir True si tuvo contacto con el otro ciudadano en tal fecha.
                 }
                 else{
                     solicitudesCounter++;

                     if(solicitudesCounter==5){
                     habilitado = false;
                     }
                 }*/



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
}
