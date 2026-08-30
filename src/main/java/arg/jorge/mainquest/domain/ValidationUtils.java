package arg.jorge.mainquest.domain;

public class ValidationUtils {

    public static boolean isEmpty(String texto){
        return texto == null || texto.isBlank();
    }

    public static boolean ISNegative(long numero){
        return numero < 0;
    }

}
