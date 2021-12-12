package walter.blockNotas.repository;

import walter.blockNotas.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserModel, Long> {
   
    @Query("SELECT u FROM UserModel u WHERE u.usuario = ?1")
    public UserModel findByUsuario(String usuario);
    
    @Query("SELECT u FROM UserModel u WHERE u.email = ?1")
    public UserModel findByEmail(String email);
    
    
}
