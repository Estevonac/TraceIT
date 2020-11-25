package Eventos;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RankingTest {


    @Test
     public void sortMayorDebeOrdenarElHashmap() throws IOException {
        Ranking unRanking = new Ranking();
        Ranking otroRanking = new Ranking();

         HashMap<String, Integer> hashmapSorteado = new HashMap<>();
         hashmapSorteado.put("Pilar",100);
         hashmapSorteado.put("Escobar", 50);
         hashmapSorteado.put("Nordelta",10);

         unRanking.anadirHashmapARanking(hashmapSorteado);

         HashMap<String, Integer> hashmapASortear = new HashMap<>();
         hashmapASortear.put("Escobar", 50);
         hashmapASortear.put("Nordelta",10);
         hashmapASortear.put("Pilar",100);

         otroRanking.anadirHashmapARanking(hashmapASortear);

         otroRanking.sortMayorEnfermos();
         assertEquals(unRanking.getRankingZonasYEnfermos(),otroRanking.getRankingZonasYEnfermos()); //Test Pasa
     }

     @Test
       public void mostrarTop3DebeSortearYMostrarSolo3()throws IOException {
      Ranking unRanking = new Ranking();
      Ranking otroRanking = new Ranking();

      HashMap<String, Integer> hashmapSorteado = new HashMap<>();
      hashmapSorteado.put("Pilar",100);
      hashmapSorteado.put("Escobar", 50);
      hashmapSorteado.put("Nordelta",10);

      unRanking.anadirHashmapARanking(hashmapSorteado);
      System.out.println(unRanking.getRankingZonasYEnfermos());

     // HashMap<String, Integer> hashmapASortear = new HashMap<>();
     // hashmapASortear.put("Escobar", 50);
      //hashmapASortear.put("Del Viso",5);
     // hashmapASortear.put("Nordelta",10);
     // hashmapASortear.put("Pilar",100);
     // System.out.println(hashmapASortear);

      //otroRanking.anadirHashmapARanking(hashmapASortear);
      //otroRanking.sortMayorEnfermos();

      //assertEquals(unRanking.mostrarRankingMayorEnfermos(),otroRanking.mostrarRankingMayorEnfermos()); //Test Pasa
     }

    @Test
    public void sortMayorEnfermos() throws IOException {
        Ranking unRanking = new Ranking();
        HashMap<String, Integer> hashmapASortear = new HashMap<>();
        hashmapASortear.put("Escobar", 50);
        hashmapASortear.put("Nordelta",10);
        hashmapASortear.put("Pilar",100);
        unRanking.anadirHashmapARanking(hashmapASortear);

        unRanking.sortMayorEnfermos();

        Ranking otroRanking = new Ranking();

        HashMap<String, Integer> hashmapSorteado = new HashMap<>();
        hashmapSorteado.put("Pilar",100);
        hashmapSorteado.put("Escobar", 50);
        hashmapSorteado.put("Nordelta",10);
        otroRanking.anadirHashmapARanking(hashmapSorteado);


        assertEquals(unRanking.getRankingZonasYEnfermos(),otroRanking.getRankingZonasYEnfermos());

    }

    @Test
    public void mostrarRankingMayorEnfermos() {
    }
}