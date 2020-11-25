package Eventos;

import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.*;

public class Ranking implements GestorDeArchivos {

    public HashMap<String, Integer> rankingZonasYEnfermos;

    // Este ranking relaciona la cantidad de enfermos por zona
    public Ranking() throws IOException { // lee el dataset de zonas que incluye contagios
        rankingZonasYEnfermos = new HashMap<>();
        //llenarRankingPorArchivo();
    }


    public void anadirARanking(String zona) {
        if (rankingZonasYEnfermos.containsKey(zona)) {
            rankingZonasYEnfermos.put(zona,rankingZonasYEnfermos.get(zona) + 1);
        }
        else{
            rankingZonasYEnfermos.put(zona,1);
        }
    }
    public void vaciarRanking() throws IOException {
        rankingZonasYEnfermos.clear();
        //llenarRankingPorArchivo();
    }

    public void eliminarDeRanking(String zona, Integer cantidad){
        rankingZonasYEnfermos.remove(zona);
    }
    public void restarUnoAZona(String zona){
        rankingZonasYEnfermos.put(zona,rankingZonasYEnfermos.get(zona) - 1);
    }

    public void anadirHashmapARanking(HashMap<String, Integer> nuevoRanking){ rankingZonasYEnfermos = nuevoRanking; }

    public void removerHashmapARanking(){rankingZonasYEnfermos.clear();}

    /*public void llenarRankingPorArchivo() throws IOException { //Lee el archivo y lo llena desde el archivo
        FileReader fileReader = new FileReader(getRuta("ZonasContagiadas"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] dato = line.split(",");
            rankingZonasYEnfermos.put(dato[0],parseInt(dato[1]));
        }
        bufferedReader.close();
    }*/ //Tengo que mejorar el format para que funcione. Separar bien el hash

    public void sortMayorEnfermos() throws IOException {

        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(rankingZonasYEnfermos.entrySet());

        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        rankingZonasYEnfermos.clear();
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        rankingZonasYEnfermos = temp;
    }
    //Mostrar solo el top 3
    public HashMap<String, Integer> mostrarRankingMayorEnfermos() throws IOException { // muestra la zona con mayor cantidad de contagios
        HashMap<String, Integer> top3 = new HashMap<>();

        for (String zonaID : rankingZonasYEnfermos.keySet()){
            if (top3.size()<3){
                top3.put(zonaID,rankingZonasYEnfermos.get(zonaID));
            }
        }
        vaciarRanking();
        anadirHashmapARanking(top3);
        sortMayorEnfermos();
        return top3;
    }
    public HashMap<String, Integer> getRankingZonasYEnfermos() { return rankingZonasYEnfermos; }
}
