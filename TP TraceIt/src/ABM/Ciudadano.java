package ABM;

import Eventos.Contacto;
import Eventos.Evento;

import java.time.LocalDateTime;
import java.util.ArrayList;



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
            estaEnfermo = false;
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


        public boolean isEstaEnfermo(Evento unEvento){

            int sintomasCounter = 0;
            for (String s: this.mostrarSintomas()) {
                for (String e: unEvento.sintomasEnfermedad) {
                    if (s.equals(e)){
                        sintomasCounter++;
                    }
                }

            }
            return this.estaEnfermo = sintomasCounter >= 2;
        }

    public void tuvoContacto(Ciudadano unCiudadano, LocalDateTime fechaDesde, LocalDateTime fechaHasta, String zona, Evento unEvento){ // Le tenemos que mandar un mensaje al otro ciudadano para confirmar.

            ArrayList<String> sintomasContacto = this.sintomas;

            for (String s: unCiudadano.sintomas) {
                    if (!sintomasContacto.contains(s)){
                        sintomasContacto.add(s);
                    }
                }
                //Anado los sintomas del contacto a ambos ciudadanos
            this.sintomas = sintomasContacto;
            unCiudadano.sintomas = sintomasContacto;
            System.out.println("Sintomas despues del contagio:" + sintomasContacto);

              // Ahora necesito que se reevalue si los ciudadanos estan enfermos.
                    this.estaEnfermo = this.isEstaEnfermo(unEvento);
                    unCiudadano.estaEnfermo = unCiudadano.isEstaEnfermo(unEvento);

               if ((!unCiudadano.estaEnfermo) && (!this.estaEnfermo)){
                    Contacto contacto = new Contacto(unCiudadano, zona,fechaDesde, fechaHasta, false);

                }
               else{
                   Contacto contacto = new Contacto(unCiudadano, zona,fechaDesde, fechaHasta, true);
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

    public boolean evaluarContacto(String respuesta){

        return respuesta.equals("true");

    }


    public void presenciaSintomas(String unSintoma){
            sintomas.add(unSintoma);

    }

    private void eliminarSintoma(Evento unEvento, String unSintoma){
            sintomas.remove(unSintoma);
    }

    public ArrayList<String> mostrarSintomas(){
            return sintomas;
    }
}
