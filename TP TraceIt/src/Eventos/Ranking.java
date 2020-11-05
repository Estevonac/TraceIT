package Eventos;

import Persistencia.GestorDeArchivos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Ranking implements GestorDeArchivos {

    HashMap<String, Integer> rankingZonasYEnfermos;

    // Este ranking relaciona la cantidad de enfermos por zona
    public Ranking() throws IOException { // lee el dataset de zonas que incluye contagios
        rankingZonasYEnfermos = new HashMap<>();
        llenarRankingPorArchivo();
    }


    public void anadirARanking(String zona, Integer cantidad){ rankingZonasYEnfermos.put(zona,cantidad);}

    public void anadirHashmapARanking(HashMap<String, Integer> nuevoRanking){ rankingZonasYEnfermos = nuevoRanking; }

    public void llenarRankingPorArchivo() throws IOException { //Lee el archivo y lo llena desde el archivo
        FileReader fileReader = new FileReader("EnfermosPorZona");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] dato = line.split(",");
            rankingZonasYEnfermos.put(dato[0],parseInt(dato[1]));
        }
        bufferedReader.close();
    }

    private void persistirRanking() throws IOException { //Despues de crearlo hay que llenarlo con los datasets
        escribirArchivo("ZonasContagiadas",getRankingZonasYEnfermos().toString());
    }
    public void sortMayorEnfermos() throws IOException { // Implementar sorts

        Object[] array = rankingZonasYEnfermos.entrySet().toArray();
        Arrays.sort(array, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        editarArchivo("ZonasContagiadas", rankingZonasYEnfermos.toString(),getRankingZonasYEnfermos().toString());
    }
    public void sortMenorEnfermos() throws IOException {

        Object[] array = rankingZonasYEnfermos.entrySet().toArray();
        Arrays.sort(array, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o1).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o2).getValue());
            }
        });
        editarArchivo("ZonasContagiadas", rankingZonasYEnfermos.toString(),getRankingZonasYEnfermos().toString());
    }

    public HashMap<String, Integer> mostrarRankingMenorEnfermos() throws IOException {
        sortMenorEnfermos();
        return getRankingZonasYEnfermos();
    }

    public HashMap<String, Integer> mostrarRankingMayorEnfermos() throws IOException { // muestra la zona con mayor cantidad de contagios
        this.sortMayorEnfermos();
        return getRankingZonasYEnfermos();

    }
    public HashMap<String, Integer> getRankingZonasYEnfermos() { return rankingZonasYEnfermos; }
}