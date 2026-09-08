package arg.jorge.mainquest.utilities;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Persistible;

import java.util.Random;

public class EntityHelper {
    public static Random random = new Random(System.currentTimeMillis());
    public static final String EMAIL_PREFIX = "mail_";
    public static final String VALID_EMAIL = "Jorge@gmail.com";


    public static Jugador crearJugadorValido(){
        return new Jugador("Jorge", "343553", VALID_EMAIL);

    }

    public static String createRandomEmail(){
        return  EMAIL_PREFIX + random.nextInt() + "@Dummy.com.ar";
    }

}
