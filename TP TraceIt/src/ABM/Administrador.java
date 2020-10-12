package ABM;

import java.util.HashMap;

public class Administrador{

    private final String nombreUsuario;
    private final String contrasena;


    public Administrador(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;

    }



    /*public void bloquearCiudadano(Ciudadano cliente, HashMap<Integer,Ciudadano> listaCiudadanos){
        if (listaCiudadanos.containsKey(cliente.getNumeroTelefono()))
            cliente.habilitado = false;
    }*/

    public void desbloquarCiudadano(Ciudadano cliente,HashMap<Integer,Ciudadano> listaCiudadanos){
        if (listaCiudadanos.containsKey(cliente.getNumeroTelefono()))
            cliente.habilitado = true;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
