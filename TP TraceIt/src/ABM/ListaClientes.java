package ABM;

import java.util.HashMap;

public class ListaClientes {
    HashMap<Integer, Ciudadano> clientes;

    public ListaClientes(){
        clientes = new HashMap<Integer, Ciudadano>();
    }

    public HashMap<Integer, Ciudadano> getHash() {
        return clientes;
    }

    public void addCliente(Ciudadano cliente) {
        if (clientes.containsKey(cliente.getNumeroTelefono())) {
            return;
        }
        clientes.put(cliente.getNumeroTelefono(), cliente);
    }

    public void removeCliente(Ciudadano cliente){
        clientes.remove(cliente.getNumeroTelefono(), cliente);
    }
}
