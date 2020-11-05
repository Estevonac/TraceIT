package ABM;

import Eventos.Enfermedad;
import Exceptions.IllegalConditionsException;

import java.io.IOException;

public class Administrador{

    protected   String nombreUsuario;
    protected   String contrasena;


    public Administrador(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;

    }

    public Enfermedad crearEnfermedad(String nombre) throws IOException {
         return new Enfermedad(nombre);
    }


    public void desbloquarCiudadano(Ciudadano unCiudadano) throws IllegalConditionsException { // podria tambien chequear que exista el ciudadano
        if (!unCiudadano.habilitado) {
            unCiudadano.habilitado = true;
            unCiudadano.solicitudesCounter = 0; // Al desbloquarlo le reseteamos sus solicitudes.
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
