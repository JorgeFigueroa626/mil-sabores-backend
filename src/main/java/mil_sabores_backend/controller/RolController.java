package mil_sabores_backend.controller;

import lombok.RequiredArgsConstructor;
import mil_sabores_backend.domain.entity.Rol;
import mil_sabores_backend.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mil_sabores_backend.constant.Constantes.*;

@RestController
@RequestMapping(API)
public class RolController {

    @Autowired
    private IRolService rolService;

    @PostMapping(ROL)
    public ResponseEntity<Rol> create(@RequestBody Rol rol){
        try {
            return ResponseEntity.ok(rolService.saveRol(rol));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(ALL_ROL)
    public ResponseEntity<List<?>> findAllRoles(){
        try {
            return ResponseEntity.ok(rolService.findAllRol());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(BY_ROL_ID)
    public ResponseEntity<?> getByRolId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(rolService.getByRolId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping(BY_ROL_ID)
    public ResponseEntity<?> updateByRolId(@PathVariable Long id, @RequestBody Rol rol){
        try {
            return ResponseEntity.ok(rolService.updateRol(id, rol));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
