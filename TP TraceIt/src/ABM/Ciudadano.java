package ABM;

import Eventos.Evento;

import java.util.ArrayList;
import java.util.Date;


public class Ciudadano extends Usuario {

        public boolean habilitado;
        int solicitudesCounter;
        int numeroTelefono;
        String cuil;
        ArrayList<String> sintomas;
        ArrayList<Ciudadano> contactos;

        public Ciudadano(String cuil, int numeroTelefono) {
            super(cuil);
            this.numeroTelefono = numeroTelefono;
            tipoUsuario = "ciudadano";
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


    public void tuvoContacto(Ciudadano unCiudadano, Date fechaDesde, Date fechaHasta){ // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.
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


    private  void presenciaSintomas(Evento unEvento, String unSintoma){
            sintomas.add(unSintoma);

    }
    private void eliminarSintoma(Evento unEvento, String unSintoma){
            sintomas.remove(unSintoma);



    }
}
