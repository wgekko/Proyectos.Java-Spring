package walter.blockNotas.repository;

import walter.blockNotas.models.NotaModel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface NotaRepository extends CrudRepository<NotaModel, Long> {
    
    @Query("SELECT a FROM NotaModel a WHERE a.titulo = ?1")
    public List<NotaModel> findApunteByTitulo(String titulo);
    
    @Query("SELECT a FROM NotaModel a WHERE a.referencia = ?1")
    public List<NotaModel> findApunteByReferencia(String referencia);
    
    
}