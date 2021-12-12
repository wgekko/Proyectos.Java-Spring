package bravi.springweb.repository;

import bravi.springweb.models.ApunteModel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ApunteRepository extends CrudRepository<ApunteModel, Long> {
    
    @Query("SELECT a FROM ApunteModel a WHERE a.materia = ?1")
    public List<ApunteModel> findApunteByMateria(String materia);
    
    @Query("SELECT a FROM ApunteModel a WHERE a.carrera = ?1")
    public List<ApunteModel> findApunteByCarrera(String carrera);
    
}