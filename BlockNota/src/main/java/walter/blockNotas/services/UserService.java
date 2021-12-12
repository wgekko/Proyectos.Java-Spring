package walter.blockNotas.services;

import walter.blockNotas.models.UserModel;
import walter.blockNotas.repository.UserRepository;
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
    
     public void borrarUsuario(Long id)
    {
        try 
        {
            userRepository.deleteById(id);
        } 
        catch (Exception e)
        {
            System.out.println("Error al intenter borrar usuario...");
            System.out.println(e.getMessage());
        }
    }  
    
    public UserModel editarUsuario(UserModel userModel)
    {
        return userRepository.save(userModel);
    }
}
