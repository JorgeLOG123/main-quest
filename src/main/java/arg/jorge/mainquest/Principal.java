package arg.jorge.mainquest;


import java.util.*;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Map<String,Jugador >  users = new HashMap<>();
        List<Mision> misions = new ArrayList<Mision>();
        misions.add(new Mision("Personalizar CV", 50));
        misions.add(new Mision("Practicar una respuesta con metodo STAR", 30));
        misions.add(new Mision("Hacer seguimiento de una postulacion", 20));
        misions.add(new Mision("Actualizar perfil de Linkedin", 40));
        misions.add(new Mision("Pedir feedback tras una entrevista", 60));
        String opcion;

        do {
            Menu.mostrarMenu();
            opcion = teclado.next();

            if (opcion.equals("1")) {
                AuthServices.Registrar(teclado, users);

            } else if (opcion.equals("2")) {
                AuthServices.login(teclado, users, misions);

            } else if (opcion.equals("3")) {
                System.out.println("Saliendo...");

            } else {
                System.out.println("OPCION NO VALIDA");
            }

        } while (!opcion.equals("3"));

        System.out.println("Muchas gracias, Hasta luego");
        teclado.close();
    }
}