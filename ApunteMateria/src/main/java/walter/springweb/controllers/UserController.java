package bravi.springweb.controllers;

import bravi.springweb.models.UserModel;
import bravi.springweb.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/users")
    public String obtenerUsuarios(Model model)
    {
        List<UserModel> listUsers = userService.obtenerUsuarios();
        model.addAttribute("listUsers", listUsers);
        
        return "users";
    }
    
}