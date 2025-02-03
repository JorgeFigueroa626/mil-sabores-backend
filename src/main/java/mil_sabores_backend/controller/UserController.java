package mil_sabores_backend.controller;

import mil_sabores_backend.domain.dto.AuthRequestDto;
import mil_sabores_backend.domain.dto.AuthResponseDto;
import mil_sabores_backend.domain.dto.SignupRequestDto;
import mil_sabores_backend.domain.dto.UserDto;
import mil_sabores_backend.domain.entity.User;
import mil_sabores_backend.repository.IUserRepository;
import mil_sabores_backend.security.JWTUtil;
import mil_sabores_backend.security.UserDetailsServiceImpl;
import mil_sabores_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static mil_sabores_backend.constant.Constantes.*;

@RestController
@RequestMapping(AUTH)
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private IUserRepository userRepository;

    @PostMapping(SIGNUP)
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto requestDto){
        if (userService.verificationCI(requestDto.getCi())) {
            return new ResponseEntity<>("Users already exist with thin CI", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createUserDto = userService.createCustomer(requestDto);
        if (createUserDto == null) {
            return new ResponseEntity<>("User not create", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(createUserDto);
    }

    @PostMapping(LOGIN)
    public AuthResponseDto authentication(@RequestBody AuthRequestDto requestDto) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getCi(), requestDto.getPassword()));
        }catch (BadCredentialsException e){
            throw  new BadCredentialsException("Incorrect CI o Password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getCi());
        Optional<User> optionalUser = userRepository.findFirstByCi(userDetails.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        if (optionalUser.isPresent()) {
            authResponseDto.setUserId(optionalUser.get().getId());
            authResponseDto.setRol(optionalUser.get().getRol().getName());
            authResponseDto.setToken(jwt);
        }
        return authResponseDto;
    }
}
