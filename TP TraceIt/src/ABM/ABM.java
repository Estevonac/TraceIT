package ABM;

import java.util.HashMap;

public class ABM extends Administrador{
    HashMap<String, Administrador> listaAdministradores;
    HashMap<Integer, Ciudadano> listaCiudadanos;

    public ABM(String nombre) {
        super(nombre);
    }


    public void addAdministrador(Administrador administrador) {
        if(listaAdministradores.containsKey(administrador.getNombreUsuario())){
            return;
        }
        listaAdministradores.put(administrador.getNombreUsuario(), administrador);
    }

    public void removeAdministrador(Administrador administrador) {
        listaAdministradores.remove(administrador.getNombreUsuario(), administrador);
    }
}
