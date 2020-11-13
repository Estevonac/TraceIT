package Persistencia;

public class Fecha {

    private final int mes, dia, hora, minuto;


    public Fecha(int dia,int mes, int hora, int minuto){
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

    public String getFechaAsString(){
        if (getDia()<10){

        }
        return (getDia() +  "/" +getMes() + " " + getHora() + ":" + getMinuto());}

    public int tiempoEntreFechasEnHoras(Fecha segundaFecha){ //Rehacer metodo

        int fecha1EnHoras = getMes()*730 + getDia() * 24 + getHora() + getMinuto()/60;
        int fecha2EnHoras = segundaFecha.getMes()*730 + segundaFecha.getDia() *24 + segundaFecha.getHora() + segundaFecha.getMinuto()/60;

        return Math.abs(fecha2EnHoras - fecha1EnHoras);

    }
}
