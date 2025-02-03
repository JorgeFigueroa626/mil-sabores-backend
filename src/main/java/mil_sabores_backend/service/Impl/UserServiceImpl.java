package mil_sabores_backend.service.Impl;

import mil_sabores_backend.domain.dto.SignupRequestDto;
import mil_sabores_backend.domain.dto.UserDto;
import mil_sabores_backend.domain.entity.Rol;
import mil_sabores_backend.domain.entity.User;
import mil_sabores_backend.domain.entity.enums.UserStatus;
import mil_sabores_backend.repository.IRolRepository;
import mil_sabores_backend.repository.IUserRepository;
import mil_sabores_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public UserDto createAdmin(SignupRequestDto requestDto) {
        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setCi(requestDto.getCi());
        user.setPassword(new BCryptPasswordEncoder().encode(requestDto.getPassword()));

        Rol userRol = rolRepository.findByName("ADMINISTRADOR");
        user.setRol(userRol);
        user.setStatus(UserStatus.ACTIVO);

        User createUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createUser.getId());
        return userDto;
    }

    @Override
    public UserDto createCustomer(SignupRequestDto requestDto) {
        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setCi(requestDto.getCi());
        user.setPassword(new BCryptPasswordEncoder().encode(requestDto.getPassword()));

        // Obtener el rol "VENTAS" de la base de datos
        Rol userRol = rolRepository.findByName("VENTAS");
        user.setRol(userRol);
        user.setStatus(UserStatus.ACTIVO);

        User createUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createUser.getId());
        return userDto;
    }

    @Override
    public boolean verificationCI(String ci) {
        return userRepository.findFirstByCi(ci).isPresent();
    }
}
