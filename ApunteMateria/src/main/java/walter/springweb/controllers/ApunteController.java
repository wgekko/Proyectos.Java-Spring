package bravi.springweb.controllers;

import bravi.springweb.models.ApunteModel;
import bravi.springweb.services.ApunteService;
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
@RequestMapping("apuntes")
public class ApunteController {
    
    @Autowired
            ApunteService apunteService;
    
    @GetMapping("")
    public String obtenerApuntes(Model model, Principal principal)
    {
        if(principal != null)
        {
            String nombre = principal.getName();
            model.addAttribute("sesion", nombre);
        }
        model.addAttribute("listaApuntes", apunteService.obtenerApuntes());
        
        return "apuntes";
    }
    
    @GetMapping("/nuevo-apunte")
    public String guardarApunte(Model model, Principal principal)
    {
        if(principal != null)
        {
            model.addAttribute("sesion", principal.getName());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("apunte", new ApunteModel());
        return "nuevo-apunte";
    }
    
    @PostMapping("/nuevo-apunte")
    public RedirectView apunteGuardado(ApunteModel apunteModel)
    {
        if(apunteModel.getApunte().equals("") || apunteModel.getCarrera().equals("") || apunteModel.getMateria().equals(""))
        {
            return new RedirectView(apunteError());
        }
       
        apunteService.guardarApunte(apunteModel);
        
        return new RedirectView("/apuntes");
    }
    
    @GetMapping("/{id}")
    public ModelAndView showApunte(@PathVariable Long id, ModelMap model, Principal principal)
    {
        if(principal != null)
        {
            model.addAttribute("sesion", principal.getName());
        }
        ApunteModel apunte = new ApunteModel();
        apunteService.findApunteById(id).ifPresent(apuntes -> {
            apunte.setId(apuntes.getId());
            apunte.setCarrera(apuntes.getCarrera());
            apunte.setMateria(apuntes.getMateria());
            apunte.setApunte(apuntes.getApunte());
            apunte.setCreatedAt(apuntes.getCreatedAt());
            apunte.setUpdatedAt(apuntes.getUpdatedAt());
        });
        model.addAttribute("apuntes", apunte);
        model.addAttribute("id", apunte.getId());
        
        return new ModelAndView("show-apunte", model);
    }
    
    @GetMapping("/apuntes/error")
    public String apunteError()
    {
        return "apuntes/error";
    }
    
    @GetMapping("/{id}/edit")
    public String editarApunte(@PathVariable Long id, Model model)
    {
        ApunteModel apunteEditar = new ApunteModel();
        apunteService.findApunteById(id).ifPresent(apuntes -> {
            apunteEditar.setId(apuntes.getId());
            apunteEditar.setCarrera(apuntes.getCarrera());
            apunteEditar.setMateria(apuntes.getMateria());
            apunteEditar.setApunte(apuntes.getApunte());
            apunteEditar.setCreatedAt(apuntes.getCreatedAt());
            apunteEditar.setUpdatedAt(LocalDateTime.now());
        });
        model.addAttribute("apunte", apunteEditar);
        return "apuntes-edit";
    }
    
    @PutMapping("/{id}/edit")
    public String apunteEditado(@PathVariable Long id, ApunteModel apunteModel)
    {
        apunteService.editarApunte(apunteModel);
        return "redirect:/apuntes";
    }
    
    @PostMapping("/{id}")
    public String borrarApunte(@PathVariable Long id)
    {
        apunteService.borrarApunte(id);
        return "redirect:/apuntes";
    }
    
}