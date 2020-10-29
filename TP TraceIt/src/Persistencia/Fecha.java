package Persistencia;

public class Fecha {

    private final int mes, dia, hora, minuto;


    public Fecha(int dia,int mes, int hora, int minuto){ //Incluir mes
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getMes() { return mes; }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getFechaAsString(){ return (getDia() +  "/" +getMes() + " " + getHora() + ":" + getMinuto());}

    public int tiempoEntreFechasEnHoras(Fecha segundaFecha){ //Rehacer metodo
        int diferenciaHoras = Math.abs(segundaFecha.getHora() - this.getHora());

       if (segundaFecha.getDia() - this.getDia() < 0){ // Estamos en diferentes meses.

       }
        int diferenciaDiasEnHoras = (segundaFecha.getDia() - this.getDia()) * 24;

        return  diferenciaDiasEnHoras + diferenciaHoras;
    }
}
