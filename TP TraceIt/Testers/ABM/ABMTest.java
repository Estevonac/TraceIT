package ABM;

import Exceptions.InvalidDataException;
import Persistencia.GestorDeArchivos;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ABMTest implements GestorDeArchivos {

    @Test
    public void addAdministradorDebeAnadirloAlArchivoPersistido() throws InvalidDataException, IOException {
        ABM abm = new ABM();
        Administrador unAdmin = new Administrador("JuanAdmin", "admin123");
        abm.addAdministrador(unAdmin);

        leerArchivo("Administradores"); // Debo chequear si lo que imprime concuerda.


    }
    @Test
    public void cambiarNombreDebeActualizarElArchivoPersistido() throws InvalidDataException, IOException {
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
    public void cargarAdministradorDebeAgregarloALaLista() throws InvalidDataException, IOException {
        ABM abm = new ABM();  //La lista debe empezar vacia y despues de cargar los datos debe estar llena
        abm.getListaAdministradores();
        abm.cargarAdministradores();
        abm.getListaAdministradores();
        leerArchivo("Administradores");
        Assert.assertEquals(abm.getListaAdministradores().size(),2);

    }

    @Test
    public void mostrarAdministradoresFromFile() throws IOException {
        leerArchivo("Administradores");
    }
}