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
        String usuarioActivo = null;

        do {
            System.out.println("-------- BIENVENIDO A MAIN QUEST ---------");

            System.out.println("Elige una opcion!");
            System.out.println("1) Registrarse ");
            System.out.println("2) Iniciar Sesion");
            System.out.println("3) Salir");
            opcion = teclado.next();

            if (opcion.equals("1")) {
                System.out.println("Ingrese su nombre de usuario: ");
                String nombre = teclado.next();
                if (users.containsKey(nombre)) {
                    System.out.println("Este usuario ya existe");

                } else {
                    System.out.println("Ingrese la contraseña");
                    String contraseña = teclado.next();

                    users.put(nombre, new Jugador(nombre, contraseña));
                    System.out.println("Bienvenido! " + nombre +  ", " + "Espero puedas lograr tus objetivos laborales del dia a dia:)");

                }


            } else if (opcion.equals("2")) {
                System.out.println("Ingrese su nombre de usuario: ");
                String nombre = teclado.next();
                if(! users.containsKey(nombre)){
                    System.out.println("Usuario no encontrado.");

                }else{
                    System.out.println("Bienvenido " + nombre + " Por favor, ingresa tu contraseña");
                    String contraseña = teclado.next();
                    if (users.get(nombre).getContraseña().equals(contraseña)){
                        System.out.println("Usuario encontrado:  " + nombre);
                        usuarioActivo = nombre;
                        String opcionJugador;
                        do{
                            System.out.println("Bienvenido de otra vez" + nombre + "por favor, seleccione una opcion");
                            System.out.println("1) Ver perfil.");
                            System.out.println("2) Ver misiones disponibles");
                            System.out.println("3) Completar misiones");
                            System.out.println("4) Cerrar sesion.");
                            opcionJugador = teclado.next();


                            if (opcionJugador.equals("1")) {
                                Jugador jugadoractual = users.get(usuarioActivo);
                                System.out.println("Mostrando datos del usuario..");
                                System.out.println(
                                                "Nombre" + jugadoractual.getNombre() +
                                                " | Experiencia" + jugadoractual.getXP() +
                                                " | Nivel" + jugadoractual.calcularNivel()

                                );


                            } else if (opcionJugador.equals("2")){
                                System.out.println("Estas son las misiones disponibles:");
                                 for(Mision m : misions){
                                 System.out.println(m.getNombre() + " - XP: " + m.getXP());
                                 }

                            }



                        }while(!opcionJugador.equals("4"));



                    } else {
                        System.out.println("Contraseña incorrecta");
                    }

                }

            } else if (opcion.equals("3")) {
                System.out.println("Aca va la opcion 3");


            } else {
                System.out.println("OPCION NO VALIDA");

            }


        } while (!opcion.equals("3"));
        System.out.println("Muchas gracias, Hasta luego");


        teclado.close();
    }

}
