package ABM;

import Exceptions.*;
import Persistencia.GestorDeArchivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ABM implements GestorDeArchivos {
    ArrayList<Administrador> listaAdministradores;
    ArrayList<Ciudadano> listaCiudadanos;

    public ABM(){
        this.listaAdministradores = new ArrayList<>(); // Obtenerla listas de los archivos de persistencia.
        this.listaCiudadanos = new ArrayList<>();

    }

    public void addAdministrador(Administrador administrador) throws InvalidDataException, IOException {
        if(listaAdministradores.contains(administrador.getNombreUsuario())){
            throw new InvalidDataException("Administrador ya existente");
        }
        else {
            String adminDatos = administrador.getNombreUsuario() + "," + administrador.getContrasena();
            escribirArchivo("Administradores",adminDatos);
            listaAdministradores.add(administrador);
        }
    }

    public void cambiarNombre(Administrador unAdmin, String nuevoNombre) throws InvalidDataException, IOException {
        if (listaAdministradores.contains(unAdmin)){
            String viejoAdmin = unAdmin.getNombreUsuario() + "," + unAdmin.getContrasena();
            String nuevoAdmin = nuevoNombre + "," + unAdmin.getContrasena();
            for (Administrador a: listaAdministradores) {
                if (a == unAdmin){

                    editarArchivo("Administradores",viejoAdmin,nuevoAdmin);
                    a.nombreUsuario = nuevoNombre;
                }
            }
        }
        else{ throw new InvalidDataException("No existe el administrador");}
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

    public ArrayList<String> getListaAdministradores(){

        ArrayList<String> listaToString = new ArrayList<>();
        for(Administrador admin : listaAdministradores){
            listaToString.add(admin.getNombreUsuario() + "," + admin.getContrasena());
        }
        return listaToString;
    }
    public void cargarAdministradores() throws IOException { //Lee los datasets y los carga en el programa

        BufferedReader bufferedReader = new BufferedReader(new FileReader(getRuta("Administradores")));
        String head = bufferedReader.readLine();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] dato = line.split(",");
            new Administrador(dato[0],dato[1]);
        }
        bufferedReader.close();

    }
}
