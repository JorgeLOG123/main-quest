package arg.jorge.mainquest;


import java.util.*;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Map<String,Jugador >  users = new HashMap<>();
        List<Mision> misions = new ArrayList<Mision>();
        // Generales (XP normal)
        misions.add(new MisionGeneral("Personalizar CV para una oferta", 50));
        misions.add(new MisionGeneral("Practicar una respuesta STAR", 30));
        misions.add(new MisionGeneral("Hacer seguimiento de una postulacion", 20));
        misions.add(new MisionGeneral("Investigar una empresa antes de postular", 25));

        // Semanales (dan el doble)
        misions.add(new MisionSemanal("Postularse a 5 ofertas", 50));
        misions.add(new MisionSemanal("Completar una entrevista", 60));
        misions.add(new MisionSemanal("Terminar un curso corto", 70));
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