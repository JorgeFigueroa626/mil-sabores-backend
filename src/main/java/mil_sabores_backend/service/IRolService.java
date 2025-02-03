package mil_sabores_backend.service;

import mil_sabores_backend.domain.entity.Rol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRolService {

    public Rol saveRol(Rol rol);

    Rol updateRol(Long id, Rol rol);

    public Rol getByRolId(Long id);

    public List<Rol> findAllRol();
}
