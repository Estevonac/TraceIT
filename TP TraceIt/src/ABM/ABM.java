package ABM;

import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.ArrayList;


public class ABM implements GestorDeArchivos {
    ArrayList<Administrador> listaAdministradores;
    ArrayList<Ciudadano> listaCiudadanos;

    public ABM(){
        this.listaAdministradores = new ArrayList<>(); // Obtenerla listas de los archivos de persistencia.
        this.listaCiudadanos = new ArrayList<>();

    }

    public void addAdministrador(Administrador administrador) throws InvalidDataException, IOException {
        if(listaAdministradores.contains(administrador.getNombreUsuario())){
            throw new InvalidDataException("Administrador ya existente");
        }
        else {
            String adminDatos = administrador.getNombreUsuario() + "," + administrador.getContrasena();
            escribirArchivo("Administradores",adminDatos);
            listaAdministradores.add(administrador);
        }
    }

    public void cambiarNombre(Administrador unAdmin, String nuevoNombre) throws InvalidDataException, IOException {
        if (listaAdministradores.contains(unAdmin)){
            for (Administrador a: listaAdministradores) {
                if (a == unAdmin){
                    editarArchivo("Administradores",a.getNombreUsuario(),nuevoNombre);
                    a.nombreUsuario = nuevoNombre;

                }

            }
        }
        else{
            throw new InvalidDataException("No existe el administrador");
        }

    }

    public void cambiarContrasena(Administrador unAdmin, String nuevaContrasena) throws InexistentUserException{
        if (listaAdministradores.contains(unAdmin)){
            for (Administrador a: listaAdministradores) {
                if (a == unAdmin){
                    a.setContrasena(nuevaContrasena);
                }

            }
        }
        else{
            throw new InexistentUserException("No existe el administrador");
        }

    }

    public void removeAdministrador(Administrador administrador) throws InexistentUserException {
        if (listaAdministradores.contains(administrador)) {
            listaAdministradores.remove(administrador);
        }
        else{
            throw new InexistentUserException("No existe el administrador");
        }
    }

    //Crear metodos para escribir a los administradores en los archivos o hacerlo directamente en los otros metodos
}
