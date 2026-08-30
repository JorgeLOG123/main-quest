package arg.jorge.mainquest.domain;
import  arg.jorge.mainquest.domain.Jugador;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


public class JugadorEntityTest {

    @Test
    public void testCrearJugadorExitosamente(){


        Jugador jugador  = new Jugador("Jorge","2509032","Ordonezguevara@gmail.com");

        assertEquals("Ordonezguevara@gmail.com", jugador.getEmail());
        assertNotNull(jugador.getNombre());
        assertNotNull(jugador.getContrasena());


    }
}