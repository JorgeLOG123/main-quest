package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.ValidationUtils;
import arg.jorge.mainquest.exceptions.MultipleJugadoresFoundException;
import arg.jorge.mainquest.forms.DeletionForm;
import arg.jorge.mainquest.forms.JugadorAdminForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import arg.jorge.mainquest.repositories.JugadorRepository;

import java.util.List;
import java.util.Optional;

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
    public List<Jugador> listartodos() {
            return this.repositorio.findAll();

    }
    @Override
    public void registrar(String nombre, String contrasena, String email){
        Jugador jugador = new Jugador(nombre, contrasena,email);

        this.guardar(jugador);
    }

    @Override
    public Jugador obtenerPorEmail(String email) {
        List<Jugador> jugadores = this.repositorio.findByEmail(email);

        Jugador encontrado = null;
        if (jugadores != null && !jugadores.isEmpty()) {
            if (jugadores.size() == 1) {
                encontrado = jugadores.get(0);
            } else {
                throw new MultipleJugadoresFoundException(email);   // con throw
            }
        }
        return encontrado;
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return !this.repositorio.findByNombre(nombre).isEmpty();
    }

    @Override
    public JugadorAdminForm prepareEntity(Long id) {
        JugadorAdminForm form = new JugadorAdminForm();

        if (ValidationUtils.isValidID(id)) {
            Optional<Jugador> jugador = this.repositorio.findById(id);
            if (jugador.isPresent()) jugador.get().copyPropertiesToForm(form);
        }

        return form;
    }

    @Override
    public Jugador obtenerPorEmail(String email, Long id) {
        Long excluir = ValidationUtils.isValidID(id) ? id : -1L;
        List<Jugador> jugadores = this.repositorio.findByEmailAndIdNot(email, excluir);

        Jugador encontrado = null;
        if (jugadores != null && !jugadores.isEmpty()) {
            if (jugadores.size() == 1) {
                encontrado = jugadores.get(0);
            } else {
                throw new MultipleJugadoresFoundException(email);
            }
        }
        return encontrado;
    }

    @Override
    public boolean existePorNombre(String nombre, Long id) {
        Long excluir = ValidationUtils.isValidID(id) ? id : -1L;
        return !this.repositorio.findByNombreAndIdNot(nombre, excluir).isEmpty();
    }

    @Override
    public void actualizar(JugadorAdminForm form) {
        Optional<Jugador> jugador = this.repositorio.findById(form.getId());
        if (jugador.isPresent()) {
            jugador.get().updatePropertiesFromForm(form);
            this.repositorio.save(jugador.get());
        }
    }

    @Override
    public void eliminar(DeletionForm form){
        if (ValidationUtils.isValidID(form.getId())){
            this.repositorio.deleteById(form.getId());

        }
    }


}
