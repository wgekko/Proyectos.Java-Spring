package bravi.springweb.services;

import bravi.springweb.models.UserModel;
import bravi.springweb.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;
         
    public List<UserModel> obtenerUsuarios()
    {
        return (List<UserModel>) userRepository.findAll();
    }
    
}
