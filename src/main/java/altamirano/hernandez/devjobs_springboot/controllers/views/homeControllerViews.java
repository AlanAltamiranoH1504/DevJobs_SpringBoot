package altamirano.hernandez.devjobs_springboot.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class homeControllerViews {

    @GetMapping("")
    public String layoutBase(Model model){
        model.addAttribute("nombrePagina", "Panel de Administración");
        model.addAttribute("tagline", "Crea y Administra tus Vacantes desde Aqui");
        model.addAttribute("barra", true);
        model.addAttribute("boton", true);

        return "layout/layout";
    }

}
