package ABM;

import Eventos.Enfermedad;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Persistencia.GestorDeArchivos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class ABM implements GestorDeArchivos {
    ArrayList<Administrador> listaAdministradores;
    ArrayList<Ciudadano> listaCiudadanos;
    ArrayList<Enfermedad> listaEnfermedades;

    public ABM(){
        this.listaAdministradores = new ArrayList<>(); // Obtenerla listas de los archivos de persistencia.
        this.listaCiudadanos = new ArrayList<>();
        this.listaEnfermedades = new ArrayList<>();

    }

    public void addAdministrador(Administrador unAdministrador) throws InvalidDataException, IOException {
        for (Administrador administrador : listaAdministradores) {
            if (!administrador.getNombreUsuario().equals(unAdministrador.getNombreUsuario())) {
                String adminDatos = unAdministrador.getNombreUsuario() + "," + unAdministrador.getContrasena();
                escribirArchivo("Administradores", adminDatos);
                listaAdministradores.add(unAdministrador);
                return;
            }
        }
        throw new InvalidDataException("Administradores ya existente");
    }

    public void agregarCiudadano(Ciudadano unCiudadano) throws InvalidDataException, IOException {
        for (Ciudadano ciudadano : listaCiudadanos) {
            if (!ciudadano.getNumeroTelefono().equals(unCiudadano.getNumeroTelefono())) {
                String ciudadanoDatos = unCiudadano.getCuil() + "," + unCiudadano.getNumeroTelefono() + "," + unCiudadano.getZona();
                escribirArchivo("AnsesCiudadanos", ciudadanoDatos);
                listaCiudadanos.add(unCiudadano);
                return;
            }
        }
        throw new InvalidDataException("Ciudadano ya existente");

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

    public ArrayList<String> getListaCiudadanos(){

        ArrayList<String> listaToString = new ArrayList<>();
        for(Ciudadano ciudadano : listaCiudadanos){
            listaToString.add(ciudadano.getCuil() + "," + ciudadano.getNumeroTelefono() + "," + ciudadano.getZona());
        }
        return listaToString;
    }
    public ArrayList<Ciudadano> getListaCiudadanosObj(){ return new ArrayList<>(listaCiudadanos); }

    public Ciudadano ciudadanoChequeo (String cuil, String telefono) throws IOException { //Chequear porque los devuelve null

        for (Ciudadano ciudadano : listaCiudadanos) {
            if (ciudadano.getCuil().equals(cuil) && ciudadano.getNumeroTelefono().equals(telefono)) { return ciudadano;}
        }
        throw new IOException("Datos incorrectos");
    }

    public Administrador adminChequeo(String nombre, String contrasena) throws IOException, InexistentUserException, InvalidDataException {

        for (Administrador admin : listaAdministradores) {
            if (admin.getNombreUsuario().equals(nombre) && admin.getContrasena().equals(contrasena)){
                return admin;
            }
        }
        throw new InvalidDataException("Datos incorrectos");
    }

    public Ciudadano getCiudadanoPorCuil(String cuil) throws InexistentUserException {
        for (Ciudadano ciudadano : listaCiudadanos){
            if (ciudadano.getCuil().equals(cuil)){ return ciudadano;}
        }
        throw new InexistentUserException("El ciudadano no existe");
    }

    public Administrador getAdminPorNombre(String nombre) throws InexistentUserException { // StackOverFlow
        for (Administrador admin : listaAdministradores){
            if (admin.getNombreUsuario().equals(nombre)){ return admin;}
        }
        throw new InexistentUserException("El admin no existe");
    }

    public void cargarAdministradores() throws IOException { //LeerArchivos
        FileReader fileReader = new FileReader(getRuta("Administradores"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] dato = line.split(",");
            listaAdministradores.add(new Administrador(dato[0],dato[1]));
        }
        bufferedReader.close();
    }
    public void cargarCiudadanos() throws IOException, InvalidDataException { //LeerArchivos
        FileReader fileReader = new FileReader(getRuta("AnsesCiudadanos"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] dato = line.split(",");
            listaCiudadanos.add(new Ciudadano(dato[0],dato[1],dato[2]));
        }
        bufferedReader.close();
    }
    public void cargarEnfermedades() throws IOException {
        FileReader fileReader = new FileReader(getRuta("Enfermedades"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] dato = line.split(",");
            ArrayList<String> sintomas = new ArrayList<>(Arrays.asList(dato).subList(1, dato.length));
           listaEnfermedades.add(new Enfermedad(dato[0],sintomas));
        }
        bufferedReader.close();
    }
}
