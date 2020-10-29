package Eventos;

import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.HashMap;

public class Ranking implements GestorDeArchivos {

    HashMap<String, Integer> rankingZonasYEnfermos;

    // Este ranking relaciona la cantidad de enfermos por zona
    public Ranking(){ // lee el dataset de zonas que incluye contagios
        rankingZonasYEnfermos = new HashMap<>();

    }

    public HashMap<String, Integer> getRankingZonasYEnfermos() {
        return rankingZonasYEnfermos;
    }

    private void llenarRanking() throws IOException { //Despues de crearlo hay que llenarlo con los datasets
        escribirArchivo("ZonasContagiadas",getRankingZonasYEnfermos().toString());
    }
    private void sortMayorEnfermos() throws IOException {
        HashMap<String, Integer> viejoRanking = new HashMap<>();


        editarArchivo("ZonasContagiadas", viejoRanking.toString(),getRankingZonasYEnfermos().toString());
    }
    private void sortMenorEnfermos() throws IOException {
        HashMap<String, Integer> viejoRanking = new HashMap<>();


        editarArchivo("ZonasContagiadas", viejoRanking.toString(),getRankingZonasYEnfermos().toString());

    }

    public HashMap<String, Integer> mostrarRankingMenorEnfermos() throws IOException {
        sortMenorEnfermos();
        return getRankingZonasYEnfermos();

    }

    public HashMap<String, Integer> mostrarRankingMayorEnfermos() throws IOException { // muestra la zona con mayor cantidad de contagios
        this.sortMayorEnfermos();
        return getRankingZonasYEnfermos();

    }

}
