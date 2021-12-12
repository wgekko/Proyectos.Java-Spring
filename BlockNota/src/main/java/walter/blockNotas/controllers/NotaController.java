package walter.blockNotas.controllers;

import walter.blockNotas.models.NotaModel;
import walter.blockNotas.services.NotaService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/notas")
public class NotaController {
    
    @Autowired
            NotaService notaService;
    
    @GetMapping("")
    public String obtenerNota(Model model, Principal principal)
    {
        if(principal != null)
        {
            String nombre = principal.getName();
            model.addAttribute("sesion", nombre);
        }
        model.addAttribute("listaNotas", notaService.obtenerNotas());
        
        return "notas";
    }
    
    @GetMapping("/nueva-nota")
    public String guardarNota(Model model, Principal principal)
    {
        if(principal != null)
        {
            model.addAttribute("sesion", principal.getName());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("nota", new NotaModel());
        return "nuevo-apunte";
    }
    
    @PostMapping("/nueva-nota")
    public RedirectView notaGuardado(NotaModel apunteModel)
    {
        if(apunteModel.getNota().equals("") || apunteModel.getTitulo().equals("") || apunteModel.getReferencia().equals(""))
        {
            return new RedirectView(notaError());
        }       
        notaService.guardarNota(apunteModel);        
        return new RedirectView("/notas");
    }
    
    @GetMapping("/{id}")
    public ModelAndView mostrarNota(@PathVariable Long id, ModelMap model, Principal principal)
    {
        if(principal != null)
        {
            model.addAttribute("sesion", principal.getName());
        }
        NotaModel nota = new NotaModel();
    
        notaService.findNotaById(id).ifPresent(notas -> {
            nota.setId(notas.getId());
            nota.setTitulo(notas.getTitulo());
            nota.setReferencia(notas.getReferencia());
            nota.setNota(notas.getNota());
            nota.setCreatedAt(notas.getCreatedAt());
            nota.setUpdatedAt(notas.getUpdatedAt());
        });
        model.addAttribute("notas", nota);
        model.addAttribute("id", nota.getId());
        
        return new ModelAndView("mostrar-nota", model);
    }
    
    @GetMapping("/notas/error")
    public String notaError()
    {
        return "notas/error";
    }
    
    @GetMapping("/{id}/edit")
    public String editarNota(@PathVariable Long id, Model model)
    {
        NotaModel notaEditar = new NotaModel();
        notaService.findNotaById(id).ifPresent(notas -> {
            notaEditar.setId(notas.getId());
            notaEditar.setTitulo(notas.getTitulo());
            notaEditar.setReferencia(notas.getReferencia());
            notaEditar.setNota(notas.getNota());
            notaEditar.setCreatedAt(notas.getCreatedAt());
            notaEditar.setUpdatedAt(LocalDateTime.now());
        });
        model.addAttribute("nota", notaEditar);
        return "notas-edit";
    }
    
    @PutMapping("/{id}/edit")
    public String notaEditado(@PathVariable Long id, NotaModel notaModel)
    {
        notaService.editarNota(notaModel);
        return "redirect:/notas";
    }
    
    @PostMapping("/{id}")
    public String borrarNota(@PathVariable Long id)
    {
        notaService.borrarNota(id);
        return "redirect:/notas";
    }
    
}