package services;

import arg.jorge.mainquest.domain.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.JugadorRepository;

import java.util.List;

@Service
public class JugadorServicesImpl implements JugadorServices {
    @Autowired
    private JugadorRepository repositorio;

    @Override
    public void guardar(Jugador jugador) {
        List<Jugador> jugadores = this.repositorio.findByNombre(jugador.getNombre());

        if(jugadores.isEmpty()){
            this.repositorio.save(jugador);
        } else {
            throw new IllegalArgumentException("Ya existe un jugador con ese nombre");
        }
    }

    @Override
    public Jugador modificar(Jugador jugador) {
        buscarPorId(jugador.getId());
        return this.repositorio.save(jugador);
    }

    @Override
    public void eliminar(Long id) {
        buscarPorId(id);
        this.repositorio.deleteById(id);
    }

    @Override
    public Jugador buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un jugador con ese id"));
    }

    @Override
    public List<Jugador> listarTodos() {
            return this.repositorio.findAll();

    }





}
