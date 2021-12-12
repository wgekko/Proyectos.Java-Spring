package bravi.springweb.services;

import bravi.springweb.models.ApunteModel;
import bravi.springweb.repository.ApunteRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApunteService {
    
    @Autowired
    ApunteRepository apunteRepository;
    
    public ArrayList<ApunteModel> obtenerApuntes()
    {
        return (ArrayList<ApunteModel>) apunteRepository.findAll();
    }
    
    public ApunteModel guardarApunte(ApunteModel apunte)
    {
        return apunteRepository.save(apunte);
    }
    
    public Optional<ApunteModel> findApunteById(Long id)
    {
        return (Optional<ApunteModel>) apunteRepository.findById(id);
    }
    
    public void borrarApunte(Long id)
    {
        try 
        {
            apunteRepository.deleteById(id);
        } 
        catch (Exception e)
        {
            System.out.println("Hubo un error al borrar");
            System.out.println(e.getMessage());
        }
    }  
    
    public ApunteModel editarApunte(ApunteModel apunteModel)
    {
        return apunteRepository.save(apunteModel);
    }
    
}