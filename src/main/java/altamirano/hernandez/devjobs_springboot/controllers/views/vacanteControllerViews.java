package altamirano.hernandez.devjobs_springboot.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacantes")
public class vacanteControllerViews {

    @GetMapping("/nueva")
    public String formNuevaVacante(Model model){
        model.addAttribute("nombrePagina", "Nueva Vacante");
        model.addAttribute("tagline", "Registra una nueva vacante gratis");
        return "vacantes/formNuevaVacante";
    }

    @GetMapping("/busqueda-vacante/{id}")
    public String detallesVacante(Model model, @PathVariable int id){
        model.addAttribute("nombrePagina", "Detalles de Vacante");
        model.addAttribute("tagline", "Detalles de la vacante");
        return "vacantes/detallesVacante";
    }

}
