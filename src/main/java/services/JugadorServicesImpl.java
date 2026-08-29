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
    public void Guardar(Jugador jugador) {

        List<Jugador> jugadores =  this.repositorio.findByName(jugador.getNombre());

        if(jugadores.isEmpty()){
            this.repositorio.save(jugador);
        }



    }



}
