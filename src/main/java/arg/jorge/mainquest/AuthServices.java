package arg.jorge.mainquest;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AuthServices {

    public static void Registrar(Scanner teclado, Map<String, Jugador> users) {


        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = teclado.next();
        if (users.containsKey(nombre)) {
            System.out.println("Este usuario ya existe");

        } else {
            System.out.println("Ingrese la contraseña");
            String contraseña = teclado.next();

            users.put(nombre, new Jugador(nombre, contraseña));
            System.out.println("Bienvenido! " + nombre + ", " + "Espero puedas lograr tus objetivos laborales del dia a dia:)");

        }


    }

    public static void login(Scanner teclado, Map<String, Jugador> users, List<Mision> misions) {

        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = teclado.next();
        if (!users.containsKey(nombre)) {
            System.out.println("Usuario no encontrado.");

        } else {
            System.out.println("Bienvenido " + nombre + " Por favor, ingresa tu contraseña");
            String contraseña = teclado.next();
            if (users.get(nombre).getContraseña().equals(contraseña)) {
                System.out.println("Usuario encontrado:  " + nombre);
            }
        }


    }

    public static void menuJugador(Scanner teclado, Jugador jugador, List<Mision> misions) {
        String opcionJugador;
        do {
            System.out.println("Bienvenido de nuevo " + jugador.getNombre() + ", seleccioná una opción");
            Menu.mostrarMenuJugador();
            opcionJugador = teclado.next();

            if (opcionJugador.equals("1")) {
                System.out.println("Mostrando datos del usuario...");
                System.out.println(
                        "Nombre: " + jugador.getNombre() +
                                " | Experiencia: " + jugador.getXP() +
                                " | Nivel: " + jugador.calcularNivel()
                );

            } else if (opcionJugador.equals("2")) {
                System.out.println("Estas son las misiones disponibles:");
                for (Mision m : misions) {
                    System.out.println(m.getNombre() + " - XP: " + m.getXP());
                }

            } else if (opcionJugador.equals("3")) {
                System.out.println("Completar misiones - proximamente");

            } else if (opcionJugador.equals("4")) {
                System.out.println("Cerrando sesion...");

            } else {
                System.out.println("Opcion no valida");
            }

        } while (!opcionJugador.equals("4"));
    }

}


