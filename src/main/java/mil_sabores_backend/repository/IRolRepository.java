package mil_sabores_backend.repository;

import mil_sabores_backend.domain.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {

    Rol findByName(String name);
}
