package ABM;

import java.util.HashMap;

public class ListaAdministradores {
    HashMap<String, Administrador> administradores;
   // ListaZonas listaZonas;
    //ListaDescuentos listaDescuentos;

    public ListaAdministradores(){
        administradores = new HashMap<>();
        //listaDescuentos = new ListaDescuentos();
        //listaZonas = new ListaZonas();
    }

    public HashMap<String, Administrador> getAdministradores() {
        return administradores;
    }

    public void addAdministrador(Administrador administrador) {
        if(administradores.containsKey(administrador.getNombreUsuario())){
            return;
        }
        administradores.put(administrador.getNombreUsuario(), administrador);
    }

    public void removeAdministrador(Administrador administrador) {
        administradores.remove(administrador.getNombreUsuario(), administrador);
    }
}