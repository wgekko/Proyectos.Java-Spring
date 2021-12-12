package bravi.springweb.repository;

import bravi.springweb.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserModel, Long> {
   
    @Query("SELECT u FROM UserModel u WHERE u.usuario = ?1")
    public UserModel findByUsername(String username);
    
    @Query("SELECT u FROM UserModel u WHERE u.email = ?1")
    public UserModel findByEmail(String email);
    
    
}
