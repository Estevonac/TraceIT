package ABM;

import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Exceptions.InvalidPasswordException;
import java.io.IOException;
import java.util.ArrayList;


public class ABM {
    ArrayList<Administrador> listaAdministradores;
    ArrayList<Ciudadano> listaCiudadanos;

    public ABM(){
        this.listaAdministradores = new ArrayList<>();
        this.listaCiudadanos = new ArrayList<>();

    }

    public void addAdministrador(Administrador administrador) throws InvalidDataException {
        if(listaAdministradores.contains(administrador.getNombreUsuario())){
            throw new InvalidDataException("Administrador ya existente");
        }
        else {
            listaAdministradores.add(administrador);
        }
    }

    public void cambiarNombre(Administrador unAdmin, String nuevoNombre) throws InvalidDataException{
        if (listaAdministradores.contains(unAdmin)){
            for (Administrador a: listaAdministradores) {
                if (a == unAdmin){
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
