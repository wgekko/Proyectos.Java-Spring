package walter.blockNotas;

import walter.blockNotas.CustomUserDetails;
import walter.blockNotas.models.UserModel;
import walter.blockNotas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsuario(usuario);
        if(user == null)
        {
            throw new UsernameNotFoundException("Usuario no encontrado....");
        }
        return new CustomUserDetails(user);
    }
    
    
    
}