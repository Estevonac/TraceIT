import ABM.*;
import Exceptions.InvalidDataException;
import java.io.IOException;
import java.util.Scanner;


public class TraceIT{ //Test del programa
    static ABM abm = new ABM();

    public static void main(String[] args) throws InvalidDataException, IOException {
        cargarArchivos();
        traceITinterfaz();
        guardarArchivos();

    }


    private static void cargarArchivos() throws IOException { //Lee los datasets y los carga en el programa
       ABM.cargarAdministradores();
       //ABM.ABM.cargarCiudadanos();
        //Eventos.enfermedad.cargarEnfermedades();
        //Eventos.Brotes.cargarBrotes();
    }
    private static void guardarArchivos() {



    }

    private static void traceITinterfaz() throws IOException {
    Scanner input = new Scanner(System.in);
        while(true){

            System.out.println("\n" + "------------------------------------" + "\n" +

                    "TraceIT" + "\n" +
                    "\n" +
                    "1. Registrarse" + "\n" +
                    "2. Iniciar sesion como Ciudadano" + "\n" +
                    "3. Iniciar sesion como Administrador" + "\n" +
                    "4. Salir" + "\n");
            switch  (input.nextInt()){
                case 1:
                    clearScreen();
                    registrar();
                    break;
                case 2:
                    clearScreen();
                    loginUsuario();
                    break;
                case 3:
                    clearScreen();
                    loginAdmin();
                    break;
                case 4:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 25; ++i) System.out.println();
    }

    private static void registrar() {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese CUIL");
        String cuil = input.nextLine();

        System.out.println("Ingrese numero de telefono");
        String telefono = input.nextLine();

        System.out.println("Ingrese zona");
        String zona = input.nextLine();
        try {
            abm.agregarCiudadano(new Ciudadano(cuil,telefono,zona));
            clearScreen();
            System.out.println("Registro existoso");

        } catch (IOException | InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loginUsuario() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese su CUIL");
        String cuil = input.nextLine();

        System.out.println("Ingrese su numero de telefono");
        String telefono = input.nextLine();

        clearScreen();
        abm.ciudadanoChequeo(cuil,telefono); // Chequea que los datos sean correctos
    }

    private static void loginAdmin() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese nombre");
        String nombre = input.nextLine();

        System.out.println("Ingrese contrasena");
        String contrasena = input.nextLine();

        clearScreen();
        abm.adminChequeo(nombre,contrasena);
    }


}




