package ABM;

import Eventos.Evento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ciudadano extends Usuario {

        private boolean habilitado;
        int solicitudesCounter;
        int numeroTelefono;
        String cuil;
        ArrayList<Evento> sintomas;

        public Ciudadano(String cuil, int numeroTelefono) {
            super(cuil);
            this.numeroTelefono = numeroTelefono;
            tipoUsuario = "ciudadano";
            habilitado = true;
            solicitudesCounter = 0;
            sintomas = new ArrayList<>();
        }


        public int getNumeroTelefono() {
            return numeroTelefono;
        }

        public String getCuil() {
            return cuil;
        }
        public void bloquear() {
            habilitado = false;
        }

        public void desbloquear() {
            habilitado = true;
        }

        public boolean getHabilitado() {
            return habilitado;
        }


    public boolean tuvoContacto(String cuil, Date fechaDesde, Date fechaHasta){ // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.
                 /*if (){                                                         // Si la respuesta es no. Contador ++. if contador = 5 usuario bloqeuado


                 }
                 else{
                     solicitudesCounter++;
                     return false;
                 }*/


            return true;
    }
    public boolean tuvoContacto(int numeroTelefono, Date fechaDesde, Date fechaHasta){

        /*if (){


        }
        else{
            solicitudesCounter++;
            return false;
        }*/
            return true;
    }

    private  void presenciaSintomas(Evento unEvento){
            sintomas.add(unEvento);

    }
    private void eliminarSintoma(Evento unEvento){
            sintomas.remove(unEvento);



    }
}
