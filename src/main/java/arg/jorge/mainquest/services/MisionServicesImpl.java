package services;

import arg.jorge.mainquest.domain.Mision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.MisionRepository;

import java.util.List;

@Service
public class MisionServicesImpl implements MisionServices {

    @Autowired
    private MisionRepository repositorio;

    @Override
    public void guardar(Mision mision) {
        this.repositorio.save(mision);
    }

    @Override
    public void eliminar(Long id) {
        buscarmisionporid(id);
        this.repositorio.deleteById(id);
    }

    @Override
    public Mision modificar(Mision mision) {
        buscarmisionporid(mision.getId());
        return this.repositorio.save(mision);
    }

    @Override
    public List<Mision> listarmisiones() {
        return this.repositorio.findAll();
    }

    @Override
    public Mision buscarmisionporid(Long id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la mision"));
    }
}