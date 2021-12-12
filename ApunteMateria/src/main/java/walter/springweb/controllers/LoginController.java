package bravi.springweb.controllers;

import bravi.springweb.models.UserModel;
import bravi.springweb.repository.UserRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class LoginController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/")
    public String index(Principal principal, Model model)
    {
        if(principal != null)
        {
            model.addAttribute("sesion", principal.getName());
        }
        return "index";
    }
    
    @GetMapping("/login")
    public RedirectView login(Principal principal)
    {
        if(principal != null)
        {
            return new RedirectView("auth/users");
        }
        return null;
    }
    
    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new UserModel());
        return "register";
    }
    
    @PostMapping("/register")
    public String proccessRegister(UserModel userModel)
    {
        
        UserModel existenteUsername = userRepository.findByUsername(userModel.getUsuario());
        if(existenteUsername != null)
        {
            return "register_error";
        }
        UserModel existenteEmail = userRepository.findByEmail(userModel.getEmail());
        if(existenteEmail != null)
        {
            return "register_error";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        
        userRepository.save(userModel);
        
        return "register_success";
    }
    
    @GetMapping("/login_error")
    public String loginError()
    {
        return "login_error";
    }

    
}

        

