import ABM.Administrador;
import ABM.Ciudadano;
import Eventos.Enfermedad;
import Exceptions.InexistentUserException;
import Exceptions.InvalidDataException;
import Persistencia.Fecha;
import Persistencia.GestorDeArchivos;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TraceIT{ //Test del programa

/*
    public static void main(String[] args) {

    loadFiles();
    homeScreen();
    saveFiles();

    }

    private static void saveFiles() {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Repositorio.ser");//creates a serial file in output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
            out.writeObject(gestorDeArchivos);// we designate our user operator to be routed
           //out.writeObject(operadorDeZonas);// we designate our zone operator to be routed
            out.close();// closes the data paths
            fileOut.close();// closes the data paths
        }catch(IOException i)//exception stuff
        {
            i.printStackTrace();
        }
    }

    private static void loadFiles() {
        try //If this doesnt work throw an exception
        {
            FileInputStream fileIn = new FileInputStream("Repositorio.ser");// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            operadorDeUsuarios = (OperadorDeUsuarios) in.readObject();// allocate it to the object file already instanciated.
            operadorDeZonas = (OperadorDeZonas) in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }catch(FileNotFoundException ignored){
        }catch(IOException i)//exception stuff
        {
            i.printStackTrace();
        }catch(ClassNotFoundException c)//more exception stuff
        {
            System.out.println("Error");
            c.printStackTrace();

        }finally {
            operadorDeUsuarios.setOperadorDeZonas(operadorDeZonas);
            operadorDeZonas.setOperadorDeUsuarios(operadorDeUsuarios);
        }
    }

    private static void homeScreen() {

        Scanner input = new Scanner(System.in);
        while(true){

            System.out.println("\n" + "------------------------------------" + "\n" +
                    "TraceIT" + "\n" +
                    "\n" +
                    "1. Crear Usuario" + "\n" +
                    "2. Iniciar sesion como Ciudadano" + "\n" +
                    "3. Iniciar sesion como Administrador" + "\n" +
                    "4. Salir" + "\n");
            switch (input.nextInt()) {
                case 1 -> {
                    clearScreen();
                    register();
                }
                case 2 -> {
                    clearScreen();
                    userLogin();
                }
                case 3 -> {
                    clearScreen();
                    adminLogin();
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
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    private static void adminLogin() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String nombreIngresado = input.nextLine();
        System.out.println("Ingrese su contrasena: ");
        String contrasenaIngresada = input.nextLine();
        try {
            gestorDeArchivos.adminCheck(nombreIngresado, contrasenaIngresada);
            showAdminScreen();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showAdminScreen() {
        clearScreen();
        System.out.println("Inicio de sesion exitosa");
        while(true){
            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +
                    "\n" +
                    "1. Crear Nuevo Admin" + "\n" +
                    "2. Eliminar Admin" + "\n" +
                    "3. Reportar Robo" + "\n" +
                    "4. Desbloquear Cliente" + "\n" +
                    "5. Eliminar Cliente" + "\n" +
                    "6. Ver lista Admins" + "\n" +
                    "7. Ver lista Clientes" + "\n" +
                    "8. Dar de alta nuevo tipo de activo" + "\n" +
                    "9. Comprar activos para zona" + "\n" +
                    "10. Cambiar hora" + "\n" +
                    "11. Agregar descuento" + "\n" +
                    "12. Eliminar descuento" + "\n" +
                    "13. Agregar terminal" + "\n" +
                    "14. Eliminar terminal" + "\n" +
                    "15. Ver ranking" + "\n" +
                    "16. Reiniciar ranking" + "\n" +
                    "17. Cerrar sesion" + "\n");

            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    clearScreen();
                    crearAdmin();
                    break;
                case 2:
                    clearScreen();
                    eliminarAdmin();
                    break;
                case 3:
                    clearScreen();
                    bloquearCliente();
                    break;
                case 4:
                    clearScreen();
                    desbloquearCliente();
                    break;
                case 5:
                    clearScreen();
                    eliminarCliente();
                    break;
                case 6:
                    clearScreen();
                    verListaAdmins();
                    break;
                case 7:
                    clearScreen();
                    verListaClientes();
                    break;
                case 8:
                    clearScreen();
                    crearTipoActivo();
                    break;
                case 9:
                    clearScreen();
                    comprarLoteActivosParaZona();
                    break;
                case 10:
                    clearScreen();
                    cambiarHoraScreen();
                    break;
                case 11:
                    clearScreen();
                    agregarDescuentoScreen();
                    break;
                case 12:
                    clearScreen();
                    eliminarDescuentoScreen();
                    break;
                case 13:
                    clearScreen();
                    agregarTerminalScreen();
                    break;
                case 14:
                    clearScreen();
                    eliminarTerminalScreen();
                    break;
                case 15:
                    clearScreen();
                    rankingScreen();
                    break;
                case 16:
                    clearScreen();
                    try {
                        operadorDeUsuarios.resetRanking();
                        System.out.println("Ranking reinciado");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 17:
                    clearScreen();
                    operadorDeUsuarios.cerrarSesion();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

 */
}

