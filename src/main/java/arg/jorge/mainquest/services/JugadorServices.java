package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;

import java.util.List;

public interface JugadorServices {

    public void guardar(Jugador jugador);

    Jugador modificar(Jugador jugador);         // Modificación

    void eliminar(Long id);                     // Baja

    Jugador buscarPorId(Long id);               // Buscar uno

    List<Jugador> listartodos();





}
