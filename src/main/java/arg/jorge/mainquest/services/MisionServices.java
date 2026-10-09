package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;

import java.util.List;

public interface MisionServices {

    void guardar(Mision mision);

    void eliminar(Long id);

    Mision modificar(Mision mision);

    List<Mision> listarmisiones();

    Mision buscarmisionporid(Long id);
}
