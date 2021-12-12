package walter.blockNotas.services;

import walter.blockNotas.models.NotaModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import walter.blockNotas.repository.NotaRepository;

@Service
public class NotaService {
    
    @Autowired
    NotaRepository notaRepository;
    
    public ArrayList<NotaModel> obtenerNotas()
    {
        return (ArrayList<NotaModel>) notaRepository.findAll();
    }
    
    public NotaModel guardarNota(NotaModel nota)
    {
        return notaRepository.save(nota);
    }
    
    public Optional<NotaModel> findNotaById(Long id)
    {
        return (Optional<NotaModel>) notaRepository.findById(id);
    }
    
    public void borrarNota(Long id)
    {
        try 
        {
            notaRepository.deleteById(id);
        } 
        catch (Exception e)
        {
            System.out.println("Error al intentar borrar la nota....");
            System.out.println(e.getMessage());
        }
    }  
    
    public NotaModel editarNota(NotaModel notaModel)
    {
        return notaRepository.save(notaModel);
    }
    
}