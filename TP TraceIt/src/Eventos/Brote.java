package Eventos;

public class Brote {

    public final String zona;
    public final int contagiados;

    /*
    Utilizar las fechas. Necesitamos saber si en un encuentro se enfermaron algunas personas y los sintomas que contrajo
    Que fecha utilizamos? la inicial o la final. Acordarse que alguien enfermo no puede asistir.
     */
    public Brote(String zona, int contagiados){ // Empezar a modelar

        this.zona = zona;
        this.contagiados = contagiados;
    }


    public String getZona(){
        return zona;
    }
    public int cantidadContagiados(){
        return contagiados;
    }
}
