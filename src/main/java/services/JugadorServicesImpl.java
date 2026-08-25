package services;

import arg.jorge.mainquest.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.JugadorRepository;

@Service
public class JugadorServicesImpl implements JugadorServices {
    @Autowired
    private JugadorRepository repositorio;


    @Override
    public void Guardar(Jugador jugador) {
        this.repositorio.save(jugador);
    }
}
