package ABM;

import Eventos.Enfermedad;

import java.util.ArrayList;
import java.util.HashMap;

public class Administrador{

    protected   String nombreUsuario;
    protected   String contrasena;


    public Administrador(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;

    }

    public Enfermedad crearEnfermedad(String nombre){
         return new Enfermedad(nombre);
    }



    public void desbloquarCiudadano(Ciudadano cliente, ArrayList<Ciudadano> listaCiudadanos){
        if (listaCiudadanos.contains(cliente.getNumeroTelefono()))
            cliente.habilitado = true;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
