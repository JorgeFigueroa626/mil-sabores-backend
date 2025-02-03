package mil_sabores_backend.service.Impl;


import mil_sabores_backend.domain.entity.Rol;
import mil_sabores_backend.repository.IRolRepository;
import mil_sabores_backend.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }


    @Override
    public Rol updateRol(Long id, Rol rol) {
        Rol updateRolId = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        updateRolId.setName(rol.getName());
        return rolRepository.save(updateRolId);
    }

    @Override
    public Rol getByRolId(Long id) {
        return rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    @Override
    public List<Rol> findAllRol() {
        return rolRepository.findAll();
    }
}
