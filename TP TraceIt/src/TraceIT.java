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
                    "8. Cerrar sesion" + "\n");

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

        System.out.print("Ingrese el nombre de la enfermedad: ");
        String enfermedad = input.nextLine();

        System.out.print("Ingrese la cantidad de sintomas relacionados: ");
        int cantidadSintomas = input.nextInt();
        ArrayList<String> sintomas = new ArrayList<>();

        for (int i = 1; i <= cantidadSintomas; i++) { //Porque no funciona????
            System.out.print("Ingrese el sintoma " + i + ": ");
            String sintoma = input.nextLine();
            sintomas.add(sintoma);
        }
        adminVigente.crearEnfermedad(enfermedad,sintomas);
    }

    private static void verListaCiudadanos() {
        System.out.println(abm.getListaCiudadanos());
    }
    private static void bloquearCiudadano() throws InvalidDataException, IllegalConditionsException { // Mejorar para que solo sea necesario el CUIL
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el CUIL del ciudadano a bloquear: ");
        String cuil = input.nextLine();

        System.out.print("Ingrese el telefono del ciudadano a bloquear: ");
        String telefono = input.nextLine();

        System.out.print("Ingrese la zona del ciudadano a bloquear: ");
        String zona = input.nextLine();

        adminVigente.bloquarCiudadano(new Ciudadano(cuil,telefono,zona));
    }
    private static void desbloquearCiudadano() throws InvalidDataException, IllegalConditionsException { // Mejorar para que solo sea necesario el CUIL
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el CUIL del ciudadano a bloquear: ");
        String cuil = input.nextLine();

        System.out.print("Ingrese el telefono del ciudadano a bloquear: ");
        String telefono = input.nextLine();

        System.out.print("Ingrese la zona del ciudadano a bloquear: ");
        String zona = input.nextLine();

        adminVigente.desbloquarCiudadano(new Ciudadano(cuil,telefono,zona));
    }

    private static void verRanking() throws IOException {
        Ranking unRanking = new Ranking();
        unRanking.mostrarRankingMayorEnfermos();

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
                    "Notificaciones: " + ciudadanoVigente.getNotificacion() + "\n");

            System.out.println("Que desea hacer?" + "\n" +
                    "1. Presentar Sintomas" + "\n" +
                    "2. Eliminar sintomas" + "\n" +
                    "3. Mostrar sintomas" + "\n" +
                    "4. Solicitar encuentro" + "\n" +
                    "5. Mostrar solicitudes" + "\n" +
                    "6. Empezar encuentro" + "\n" +
                    "7. Cerrar sesion" + "\n");

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
                    return;
                }
                case 7 -> {
                    clearScreen();
                    mostrarBrotes();
                    return;
                }
                case 8 -> {
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

    private static void solicitarUnEncuentro() throws InexistentUserException {
        Scanner input = new Scanner(System.in);
        ArrayList<Ciudadano> participantes = new ArrayList<>();

        System.out.println("Ingresa la cantidad de participantes");
        int cantParticipantes = input.nextInt();

        for (int i = 1; i <= cantParticipantes; i++) {
            System.out.print("Ingrese el Cuil del participante " + i + ": ");
            String cuil = input.nextLine();
            participantes.add(abm.getCiudadanoPorCuil(cuil));
        }
        System.out.print("Ingrese la fecha de inicio, en formato d/m H:M : ");
        Fecha fechaDesde = toFecha(input.nextLine());

        System.out.print("Ingrese la fecha de finalizacion, en formato d/m H:M : ");
        Fecha fechaHasta = toFecha(input.nextLine());

        System.out.println("Ingrese la zona del encuentro: ");
        String zona = input.nextLine();

        ciudadanoVigente.solicitarContacto(participantes,fechaDesde,fechaHasta,zona);
    }

    private static void mostrarSolicitudes() throws InexistentUserException, InvalidDataException, IOException, IllegalConditionsException {
        Scanner input = new Scanner(System.in);
        System.out.println("Solicitudes recibidas: ");
        ciudadanoVigente.mostrarSolicitudesRecibidas();

        System.out.print("Desea aceptar / rechazar alguna? ");
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
}




