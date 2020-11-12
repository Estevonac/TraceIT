package Persistencia;
import java.io.*;


public interface GestorDeArchivos { // Interfaz que se va a encargar de la persistencia.

    static String getRuta(String archivo) throws IOException {
        return "TP TraceIT/src/Datasets/" + archivo + ".txt";
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
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getRuta(archivo),true));

        bufferedWriter.write(aEscribir); // Escribimos y hacemos un salto de linea
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        }


       default void editarArchivo(String archivo, String aReemplazar, String aEscribir) throws IOException {
        String archivoBase = getRuta(archivo);
        String tmpFileName = "temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoBase)); BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(aReemplazar)) {
                    line = line.replace(aReemplazar, aEscribir);
                }
                bw.write(line + "\n");
            }

        } catch (Exception e) {
            return;
        }
        // Elimino el archivo viejo
        File archivoViejo = new File(archivoBase);
        archivoViejo.delete();

        // le cambio el nombre del temporal al nuevo
        File archivoNuevo = new File(tmpFileName);
        archivoNuevo.renameTo(archivoViejo);
    }
}
