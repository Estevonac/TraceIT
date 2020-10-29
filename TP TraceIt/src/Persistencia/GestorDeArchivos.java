package Persistencia;
import java.io.*;

public interface GestorDeArchivos { // Interfaz que se va a encargar de la persistencia.

    default String getRuta(String archivo) throws IOException {
        return "src/Datasets/" + archivo + ".txt";
    }


    default void leerArchivo(String archivo) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(getRuta(archivo)));

        String head = bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    default void escribirArchivo(String archivo, String aEscribir) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getRuta(archivo)));

        bufferedWriter.write(aEscribir); // Escribimos y hacemos un salto de linea
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    default void editarArchivo(String archivo, String aReemplazar, String aEscribir) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(getRuta(archivo)));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals(aReemplazar)){
                line = aEscribir; // Funciona esto?
            }
        }
        bufferedReader.close();

    }
}
