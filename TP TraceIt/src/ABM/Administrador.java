package ABM;

import Eventos.Enfermedad;
import Exceptions.IllegalConditionsException;
import Persistencia.GestorDeArchivos;

import java.io.IOException;
import java.util.ArrayList;

public class Administrador implements GestorDeArchivos {

    protected   String nombreUsuario;
    protected   String contrasena;


    public Administrador(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;

    }

    public Enfermedad crearEnfermedad(String nombre) throws IOException {
         return new Enfermedad(nombre);
    }
    public Enfermedad crearEnfermedad(String nombre, ArrayList<String> sintomas) throws IOException {
        return new Enfermedad(nombre, sintomas);
    }


    public void desbloquarCiudadano(Ciudadano unCiudadano) throws IllegalConditionsException { // podria tambien chequear que exista el ciudadano
        if (!unCiudadano.habilitado) {
            unCiudadano.habilitado = true;
            unCiudadano.solicitudesCounter = 0; // Al desbloquarlo le reseteamos sus solicitudes.
        }
        else {throw new IllegalConditionsException("El ciudadano ya esta desbloqueado");}
    }

    public void bloquarCiudadano(Ciudadano unCiudadano) throws IllegalConditionsException { // podria tambien chequear que exista el ciudadano
        if (unCiudadano.habilitado) {
            unCiudadano.habilitado = false;

        }
        else {throw new IllegalConditionsException("El ciudadano ya esta desbloqueado");}
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    protected void setContrasena(String nuevaContrasena) {
        contrasena = nuevaContrasena;
    }

    protected String getContrasena() { return contrasena;}


}
