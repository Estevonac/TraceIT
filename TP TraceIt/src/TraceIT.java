import ABM.ABM;
import ABM.Administrador;
import ABM.Ciudadano;
import Eventos.*;
import Exceptions.IllegalConditionsException;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Persistencia.Fecha;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TraceIT{
    static ABM abm = new ABM();
    static Administrador adminVigente;
    static Ciudadano ciudadanoVigente;
    static Ranking ranking;
    static ArrayList<String> listaBrotes = new ArrayList<>();
    static {
        try {
            ranking = new Ranking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InvalidDataException, IOException, IllegalConditionsException, InexistentUserException {
        cargarArchivos();
        traceITinterfaz();
    }


    private static void cargarArchivos() throws IOException, InvalidDataException { //Lee los datasets y los carga en el programa
       abm.cargarAdministradores();
       abm.cargarCiudadanos();
       abm.cargarEnfermedades();
       //cargarBrotes();
    }

    private static void cargarBrotes() throws IOException {
        FileReader fileReader = new FileReader("TP TraceIT/src/Datasets/Brotes.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String head = bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] broteDatos = line.split(",");
            listaBrotes.add(broteDatos[0] + ", " + broteDatos[1] + ", " + broteDatos[2]);
        }
        bufferedReader.close();
    }

    private static void traceITinterfaz() throws IOException, InvalidDataException, IllegalConditionsException, InexistentUserException {
    Scanner input = new Scanner(System.in);
        while(true){

            System.out.println("\n" + "------------------------------------" + "\n" +

                    "TraceIT" + "\n" +
                    "\n" +
                    "1. Registrarse" + "\n" +
                    "2. Iniciar sesion como Ciudadano" + "\n" +
                    "3. Iniciar sesion como Administrador" + "\n" +
                    "4. Salir" + "\n");
            switch (input.nextInt()) {
                case 1 -> {
                    clearScreen();
                    registrar();
                }
                case 2 -> {
                    clearScreen();
                    loginUsuario();
                }
                case 3 -> {
                    clearScreen();
                    loginAdmin();
                }
                case 4 -> {
                    clearScreen();
                    System.out.println("Gracias por usar TraceIT");
                    System.exit(0);
                    return;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 25; ++i) System.out.println();
    }

    private static void registrar() {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese CUIL: ");
        String cuil = input.nextLine();

        System.out.print("Ingrese numero de telefono: ");
        String telefono = input.nextLine();

        System.out.print("Ingrese zona: ");
        String zona = input.nextLine();
        try {
            abm.agregarCiudadano(new Ciudadano(cuil,telefono,zona));
            clearScreen();
            System.out.println("Registro existoso");

        } catch (IOException | InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loginUsuario() throws IOException, InvalidDataException, IllegalConditionsException, InexistentUserException {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese su CUIL: ");
        String cuil = input.nextLine();

        System.out.print("Ingrese su numero de telefono: ");
        String telefono = input.nextLine();

        clearScreen();
        ciudadanoVigente = abm.ciudadanoChequeo(cuil,telefono); // Chequea que los datos sean correctos
        pantallaCiudadano();

    }

    private static void loginAdmin() throws IOException, InvalidDataException, InexistentUserException, IllegalConditionsException {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese nombre: ");
        String nombre = input.nextLine();

        System.out.print("Ingrese contrasena: ");
        String contrasena = input.nextLine();

        clearScreen();
        adminVigente = abm.adminChequeo(nombre,contrasena);
        pantallaAdmin();
    }

    private static void pantallaAdmin() throws InvalidDataException, IOException, InexistentUserException, IllegalConditionsException {
        Scanner scanner = new Scanner(System.in);

        clearScreen();
        System.out.println("Inicio de sesion exitosa");
        while(true){
            System.out.println("\n" + "------------------------------------" + "\n" +

                    "TraceIT Administradores" + "\n" +
                    "1. Crear Nuevo Admin" + "\n" +
                    "2. Eliminar Admin" + "\n" +
                    "3. Crear enfermedad" + "\n" +
                    "4. Ver lista Clientes" + "\n" +
                    "5. Bloquear ciudadano" + "\n" +
                    "6. Desbloquear ciudadano" + "\n" +
                    "7. Ver ranking" + "\n" +
                    "8. Ver enfermedades existentes" + "\n" +
                    "9. Cerrar sesion" + "\n");

            switch (scanner.nextInt()) {
                case 1 -> {
                    clearScreen();
                    crearAdmin();
                }
                case 2 -> {
                    clearScreen();
                    eliminarAdmin();
                }
                case 3 -> {
                    clearScreen();
                    crearEnfermedad();
                }
                case 4 -> {
                    clearScreen();
                    verListaCiudadanos();
                }
                case 5 -> {
                    clearScreen();
                    bloquearCiudadano();
                }
                case 6 -> {
                    clearScreen();
                    desbloquearCiudadano();
                }
                case 7 -> {
                    clearScreen();
                    verRanking();
                }
                case 8 -> {
                    clearScreen();
                    mostrarEnfermedadesVigentes();
                }
                case 9 -> {
                    clearScreen();
                    adminVigente = null;
                    traceITinterfaz();
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private static void crearAdmin() throws InvalidDataException, IOException {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese nombre de administrador: ");
        String nombre = input.nextLine();

        System.out.print("Ingrese la contraseña");
        String contrasena = input.nextLine();

        abm.addAdministrador(new Administrador(nombre,contrasena));
    }

    private static void eliminarAdmin() throws InexistentUserException {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese nombre de administrador: ");
        String nombre = input.nextLine();

        System.out.print("Ingrese la contraseña");
        String contrasena = input.nextLine();

        abm.removeAdministrador(new Administrador(nombre,contrasena));
    }

    private static void crearEnfermedad() throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<String> sintomas = new ArrayList<>();
        System.out.print("Ingrese el nombre de la enfermedad: ");
        String enfermedad = input.nextLine();

        System.out.print("Ingrese la cantidad de sintomas relacionados: ");
        int cantidadSintomas = Integer.parseInt(input.nextLine());

        for (int i = 1; i <= cantidadSintomas; i++) { //Porque no funciona????
            System.out.print("Ingrese el sintoma " + i + ": ");
            sintomas.add(input.nextLine());
        }
        adminVigente.crearEnfermedad(enfermedad,sintomas);
    }

    private static void verListaCiudadanos() {
        System.out.println(abm.getListaCiudadanos());
    }

    private static void bloquearCiudadano() throws InvalidDataException, IllegalConditionsException, InexistentUserException {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el CUIL del ciudadano a bloquear: ");
        String cuil = input.nextLine();

        Ciudadano unCiudadano = abm.getCiudadanoPorCuil(cuil);
        adminVigente.bloquarCiudadano(unCiudadano);
    }
    private static void desbloquearCiudadano() throws InvalidDataException, IllegalConditionsException, InexistentUserException { // Mejorar para que solo sea necesario el CUIL
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el CUIL del ciudadano a bloquear: ");
        String cuil = input.nextLine();

        Ciudadano unCiudadano = abm.getCiudadanoPorCuil(cuil);
        adminVigente.desbloquarCiudadano(unCiudadano);
    }

    private static void verRanking() throws IOException, InvalidDataException, InexistentUserException, IllegalConditionsException {
        if (ciudadanoVigente != null){
            System.out.println(ranking.mostrarRankingMayorEnfermos());
            pantallaCiudadano();
        }
        else{
            System.out.println(ranking.mostrarRankingMayorEnfermos());
            pantallaAdmin();
        }


    }

    private static void pantallaCiudadano() throws IOException, InexistentUserException, InvalidDataException, IllegalConditionsException {
        Scanner input = new Scanner(System.in);
        System.out.println("Inicio de sesion exitosa");
        while (true) {
            System.out.println("\n" + "------------------------------------" + "\n" +

                    "TraceIT Ciudadanos" + "\n" +
                    "Cuil: " + ciudadanoVigente.getCuil() + "\n" +
                    "Sintomas: " + ciudadanoVigente.mostrarSintomas() + "\n" +
                    "Enfermedad: " + ciudadanoVigente.getNombreEnfermedadActual() + "\n" + "\n" +
                    "Notificaciones: " + ciudadanoVigente.getNotificacion() + notificacionEncuentro() +"\n");

            System.out.println("Que desea hacer?" + "\n" +
                    "1. Presentar Sintomas" + "\n" +
                    "2. Eliminar sintomas" + "\n" +
                    "3. Mostrar sintomas" + "\n" +
                    "4. Solicitar encuentro" + "\n" +
                    "5. Mostrar solicitudes" + "\n" +
                    "6. Empezar encuentro" + "\n" +
                    "7. Ver brotes" + "\n" +
                    "8. Ver enfermedades existentes" + "\n" +
                    "9. Ver ranking" + "\n" +
                    "10. Cerrar sesion" + "\n");

            switch (input.nextInt()) {
                case 1 -> {
                    clearScreen();
                    presentarSintomas();
                }
                case 2 -> {
                    clearScreen();
                    eliminarSintomas();
                }
                case 3 -> {
                    clearScreen();
                    mostrarSintomas();
                }
                case 4 -> {
                    clearScreen();
                    solicitarUnEncuentro();
                }
                case 5 -> {
                    clearScreen();
                    mostrarSolicitudes();
                    return;
                }
                case 6 -> {
                    clearScreen();
                    ciudadanoVigente.empezarEncuentro();
                    System.out.println("Evento simulado con exito");
                    pantallaCiudadano();
                    return;
                }
                case 7 -> {
                    clearScreen();
                    mostrarBrotes();
                    return;
                }
                case 8 -> {
                    clearScreen();
                    mostrarEnfermedadesVigentes();
                    return;
                }
                case 9 -> {
                    clearScreen();
                    verRanking();
                    return;
                }
                case 10 -> {
                    clearScreen();
                    traceITinterfaz();
                    return;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }


    private static void presentarSintomas() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el sintoma: ");
        String sintoma = input.nextLine();

        System.out.println("Ingrese la fecha en formato dd/mm hh:mm: ");
        String fecha = input.nextLine();

        ciudadanoVigente.presenciaSintomas(sintoma, toFecha(fecha));
        if (ciudadanoVigente.estaEnfermo){
            ranking.anadirARanking(ciudadanoVigente.getZona(),1);
        }
    }


    private static Fecha toFecha(String fecha){
        int dia = Integer.parseInt(fecha.substring(0,2));
        int mes = Integer.parseInt(fecha.substring(3,5));
        int hora = Integer.parseInt(fecha.substring(6,8));
        int minuto = Integer.parseInt(fecha.substring(9));

        return new Fecha(dia,mes,hora,minuto);
    }

    private static void eliminarSintomas() throws IOException {
        Scanner input  = new Scanner(System.in);

        System.out.println("Ingrese el sintoma: ");
        String sintoma = input.nextLine();

        System.out.println("Ingrese la fecha en formato dd/mm hh:mm: ");
        String fecha = input.nextLine();

        ciudadanoVigente.eliminarSintoma(sintoma,toFecha(fecha));
    }

    private static void mostrarSintomas() {
        ciudadanoVigente.mostrarSintomas();
    }

    private static void solicitarUnEncuentro() throws InexistentUserException, InvalidDataException, IOException, IllegalConditionsException {
        Scanner input = new Scanner(System.in);
        ArrayList<Ciudadano> participantes = new ArrayList<>();

        if (ciudadanoVigente.estaEnfermo){
            System.out.println("Estas enfermo, mejor quedate en casa");
            pantallaCiudadano();
        }

        System.out.print("Ingresa la cantidad de participantes: ");
        int cantParticipantes = Integer.parseInt(input.nextLine());

        for (int i = 1; i <= cantParticipantes; i++) {
            System.out.print("Ingrese el Cuil del participante " + i + ": ");
            String cuil = input.nextLine();
            participantes.add(abm.getCiudadanoPorCuil(cuil));
        }
        System.out.print("Ingrese la fecha de inicio, en formato dd/mm HH:MM : ");
        Fecha fechaDesde = toFecha(input.nextLine());

        System.out.print("Ingrese la fecha de finalizacion, en formato dd/mm HH:MM : ");
        Fecha fechaHasta = toFecha(input.nextLine());

        System.out.print("Ingrese la zona del encuentro: ");
        String zona = input.nextLine();

        ciudadanoVigente.solicitarContacto(participantes,fechaDesde,fechaHasta,zona);
    }

    private static String notificacionEncuentro() {
        if (ciudadanoVigente.solicitudesRecibidas.size() == 1){
            return ("Tenes una solicitud recibida, anda a mostrar solicitudes");
        }
        else if(ciudadanoVigente.solicitudesRecibidas.size() > 1){
            return ("Tenes " + ciudadanoVigente.solicitudesRecibidas.size() + " solicitues recibidas, anda a mostrar solicitudes");
        }
        else{
            return ("No recibiste ninguna solicitud de encuentro");
        }
    }
    private static void mostrarSolicitudes() throws InexistentUserException, InvalidDataException, IOException, IllegalConditionsException {
        Scanner input = new Scanner(System.in);
        ArrayList<String> cuilsEmisores = new ArrayList<>();
        for (SolicitudEncuentro unaSolicitud : ciudadanoVigente.mostrarSolicitudesRecibidas()){
            cuilsEmisores.add(unaSolicitud.getEmisor().getCuil());
        }
        System.out.println("Cuils de los emisores de las solicitudes recibidas: " + cuilsEmisores.toString() + "\n") ;

        System.out.print("Desea aceptar / rechazar alguna? Si o No: ");
        String respuesta = input.nextLine();

        if (respuesta.equalsIgnoreCase("si")){
            System.out.println("La solicitud de que emisor quieres aceptar/rechazar? Introduce su CUIL: ");
            String cuil = input.nextLine();
            Ciudadano emisor = abm.getCiudadanoPorCuil(cuil);

            System.out.println("Desea aceptarla o rechazarla?");
            String respuesta2 = input.nextLine();

            if (respuesta2.equalsIgnoreCase("aceptar")){
                ciudadanoVigente.aceptarSolicitud(emisor.getSolicitudEnviada());
            }
            else if(respuesta2.equalsIgnoreCase("rechazar")){
                ciudadanoVigente.rechazarSolicitud(emisor.getSolicitudEnviada());
            }
            else {
                System.out.println("La respuesta puede ser aceptar o rechazar. Proba nuevamente");
            }
        }
        else if(respuesta.equalsIgnoreCase("no")){
            pantallaCiudadano();
        }
        else{
            System.out.println("La respuesta puede ser si o no. Proba nuevamente");
            mostrarSolicitudes();
        }
    }

    static void mostrarBrotes(){
        System.out.println("Brotes: ");
        System.out.println("Enfermedad,  Zona,  Cantidad de enfermos");
        for (String listaBrote : listaBrotes) {
            System.out.println(listaBrote);
        }
    }
    private static void mostrarEnfermedadesVigentes() throws IOException {
        ArrayList<String> enfermedades = new ArrayList<>();
        if (ciudadanoVigente != null) {
            for (Enfermedad enfermedad : ciudadanoVigente.getEnfermedadesVigentes()) {
                enfermedades.add(enfermedad.getNombre());
            }
        } else {
            for (Enfermedad enfermedad : adminVigente.getEnfermedadesVigentes()) {
                enfermedades.add(enfermedad.getNombre());
            }
            System.out.println(enfermedades);
        }
    }
}