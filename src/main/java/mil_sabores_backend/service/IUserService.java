package mil_sabores_backend.service;

import mil_sabores_backend.domain.dto.SignupRequestDto;
import mil_sabores_backend.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    UserDto createAdmin(SignupRequestDto requestDto);

    UserDto createCustomer(SignupRequestDto requestDto);

    boolean verificationCI(String ci);

}
