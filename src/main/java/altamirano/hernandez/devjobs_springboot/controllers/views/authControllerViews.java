package altamirano.hernandez.devjobs_springboot.controllers.views;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class authControllerViews {

    @GetMapping("/crear-cuenta")
    public String formularioRegistroUsuarios(Model model){
        model.addAttribute("nombrePagina", "Crea Tu Cuenta en DevJobs");
        model.addAttribute("tagline", "Comienza a Publicar tus Vacantes Gratis, Solo Debes Crear Una Cuenta");

        return "auth/formularioRegistroUsuarios";
    }

    @GetMapping("/")
    public String formularioLogin(Model model){
        model.addAttribute("nombrePagina", "Inicia Sesión en DevJobs");
        model.addAttribute("tagline", "Incia Sesión y Empieza Publicar tus Vacantes");

        return "auth/formularioLogin";
    }

    @GetMapping("/dev-jobs")
    public String devJobsSinSesion(Model model){
        model.addAttribute("nombrePagina", "Vacantes Disponibles en DevJobs");
        model.addAttribute("tagline", "Registrate y Empieza a Publicar tus Vacantes");

        return "auth/devJobs";
    }

    @GetMapping("/dev-jobs/detalles/vacante/{id}")
    public String detallesVacanteAreaPublica(Model model){
        model.addAttribute("nombrePagina", "Conoce la vacante");
        model.addAttribute("tagline", "Conoce la vacante");

        return "areaPublica/detallesVacante";
    }
}
