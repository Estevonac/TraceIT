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

}