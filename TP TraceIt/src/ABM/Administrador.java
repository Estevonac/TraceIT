package ABM;

public class Administrador extends Usuario{
    ListaAdministradores listaAdministradores;
    ListaClientes listaClientes;


    public Administrador(String nombre){
        super(nombre);
        tipoUsuario = "administrador";
        listaAdministradores = new ListaAdministradores();
        listaClientes = new ListaClientes();

    }

    public void addAdministrador(Administrador administrador, ListaAdministradores listaAdministradores) {
        listaAdministradores.addAdministrador(administrador);
    }

    public void removeAdministrador(Administrador administrador, ListaAdministradores listaAdministradores){
        for (int i = 0; i < listaAdministradores.getAdministradores().size(); i++) {
            if (listaAdministradores.getAdministradores().get(i).getNombreUsuario().equalsIgnoreCase(administrador.getNombreUsuario()))
                listaAdministradores.removeAdministrador(administrador);
        }
    }

    public void bloquearCiudadano(Ciudadano cliente, ListaClientes listaClientes){
        if (listaClientes.getHash().containsKey(cliente.getNumeroTelefono()))
            cliente.bloquear();
    }

    public void desbloquarCiudadano(Ciudadano cliente, ListaClientes listaClientes){
        if (listaClientes.getHash().containsKey(cliente.getNumeroTelefono()))
            cliente.desbloquear();
    }


}
