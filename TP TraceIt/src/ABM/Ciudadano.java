package ABM;

import Eventos.Contacto;
import Eventos.Evento;

import java.util.ArrayList;
import java.util.Date;


public class Ciudadano{

    public boolean habilitado;
    public boolean estaEnfermo;
    int solicitudesCounter;
    int numeroTelefono;
    private final String cuil;
    ArrayList<String> sintomas;
    ArrayList<Ciudadano> contactos;



        public Ciudadano(String cuil, int numeroTelefono) {
            this.cuil = cuil;
            this.numeroTelefono = numeroTelefono;
            habilitado = true;
            solicitudesCounter = 0;
            sintomas = new ArrayList<>();
            contactos = new ArrayList<>();
        }


        public int getNumeroTelefono() {
            return numeroTelefono;
        }

        public String getCuil() {
            return cuil;
        }

        public boolean getHabilitado() {
            return habilitado;
        }


    public void tuvoContacto(Ciudadano unCiudadano, Date fechaDesde, Date fechaHasta, String zona, Evento unEvento){ // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.

            for (String s: unCiudadano.sintomas) {
                for (String e: this.sintomas) {
                    if (!s.equals(e)){
                     this.presenciaSintomas(s); // Ahora necesito que se reevalue si los ciudadanos estan enfermos.
                 }
                }
             }
                    unEvento.estaEnfermo(this);
                    unEvento.estaEnfermo(unCiudadano);
                if ((!unCiudadano.estaEnfermo) && (!this.estaEnfermo)){
                    Contacto contacto = new Contacto(unCiudadano, zona,fechaDesde, fechaHasta, false);

                }



                 /*if (){                                                         // Si la respuesta es no. Contador ++. if contador = 5 usuario bloqeuado

  // Podemos tener un boolean que lo recibe unCiudadano y tiene que decir True si tuvo contacto con el otro ciudadano en tal fecha.
                 }
                 else{
                     solicitudesCounter++;

                     if(solicitudesCounter==5){
                     habilitado = false;
                     }
                 }*/



    }

    public boolean evaluarContacto(String respuesta){

            if (respuesta.equals("true")){
                return true;
            }
            else{return false;}

    }


    private  void presenciaSintomas(String unSintoma){
            sintomas.add(unSintoma);

    }

    private void eliminarSintoma(Evento unEvento, String unSintoma){
            sintomas.remove(unSintoma);
    }

    public ArrayList<String> mostrarSintomas(){
            return sintomas;
    }
}
