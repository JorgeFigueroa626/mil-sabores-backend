package mil_sabores_backend.security;

import mil_sabores_backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import mil_sabores_backend.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String ci) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findFirstByCi(ci);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("Username not found", null);
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getCi(), optionalUser.get().getPassword(), new ArrayList<>());
    }
}
