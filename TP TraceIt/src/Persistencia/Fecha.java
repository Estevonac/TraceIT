package Persistencia;

public class Fecha {

    private final int dia, hora, minuto;


    public Fecha(int dia, int hora, int minuto){
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getFechaAsString(){ return (getDia() + " " + getHora() + ":" + getMinuto());}

    public int tiempoEntreFechasEnHoras(Fecha segundaFecha){

        int diferenciaDiasEnHoras = (segundaFecha.getDia() - this.getDia()) * 24;
        int diferenciaHoras = Math.abs(segundaFecha.getHora() - this.getHora());
        //int diferenciaMinutos = Math.abs(segundaFecha.getMinuto() - this.getMinuto());

        return diferenciaDiasEnHoras + diferenciaHoras;
    }
}
