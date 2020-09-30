package ABM;

import java.util.HashMap;

public class Administrador extends Usuario{



    public Administrador(String nombre){
        super(nombre);
        tipoUsuario = "administrador";


    }



    public void bloquearCiudadano(Ciudadano cliente, HashMap<Integer,Ciudadano> listaCiudadanos){
        if (listaCiudadanos.containsKey(cliente.getNumeroTelefono()))
            cliente.habilitado = false;
    }

    public void desbloquarCiudadano(Ciudadano cliente,HashMap<Integer,Ciudadano> listaCiudadanos){
        if (listaCiudadanos.containsKey(cliente.getNumeroTelefono()))
            cliente.habilitado = true;
    }


}
