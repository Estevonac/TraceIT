package ABM;

import Exceptions.InvalidDataException;
import Persistencia.GestorDeArchivos;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ABMTest implements GestorDeArchivos {

    @Test
    public void addAdministrador() throws InvalidDataException, IOException {
        ABM abm = new ABM();
        Administrador unAdmin = new Administrador("JuanAdmin", "admin123");
        abm.addAdministrador(unAdmin);

        leerArchivo("Administradores"); // Debo chequear si lo que imprime concuerda.


    }
    @Test
    public void cambiarNombreShouldUpdateTheFile() throws InvalidDataException, IOException {
        ABM abm = new ABM();
        Administrador unAdmin = new Administrador("juansito", "admin123");
        Administrador otroADmin = new Administrador("jorge","jorge123");
        abm.addAdministrador(unAdmin);


        abm.cambiarNombre(unAdmin,"nuevoJuansito");
        abm.addAdministrador(otroADmin);
        System.out.println(abm.getListaAdministradores());
        // Como lo chequeo por metodo?

        // Chequeado manualmente en datasets y funciona.


    }

    @Test
    public void mostrarAdministradoresFromFile() throws IOException {
        leerArchivo("Administradores"); // Como chequeo con assert?
    }
}