package altamirano.hernandez.devjobs_springboot.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class authController {

    @GetMapping("/crear-cuenta")
    public String formularioRegistroUsuarios(Model model){
        model.addAttribute("nombrePagina", "Crea Tu Cuenta en DevJobs");
        model.addAttribute("tagline", "Comienza a Publicar tus Vacantes Gratis, Solo Debes Crear Una Cuenta");

        return "auth/formularioRegistroUsuarios";
    }
}
