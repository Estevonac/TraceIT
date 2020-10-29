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
}