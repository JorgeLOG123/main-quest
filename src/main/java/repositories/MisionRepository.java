package repositories;

import arg.jorge.mainquest.domain.Mision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MisionRepository extends JpaRepository<Mision, Long> {
}
