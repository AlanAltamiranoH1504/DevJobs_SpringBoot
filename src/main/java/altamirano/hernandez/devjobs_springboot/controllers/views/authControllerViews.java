package altamirano.hernandez.devjobs_springboot.controllers.views;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class authControllerViews {

    @GetMapping("/crear-cuenta")
    public String formularioRegistroUsuarios(Model model) {
        model.addAttribute("nombrePagina", "Crea Tu Cuenta en DevJobs");
        model.addAttribute("tagline", "Comienza a Publicar tus Vacantes Gratis, Solo Debes Crear Una Cuenta");

        return "auth/formularioRegistroUsuarios";
    }

    @GetMapping("/olvide-password")
    public String formOlvidePassword(Model model) {
        model.addAttribute("nombrePagina", "Reestablecer Password");
        model.addAttribute("tagline", "Rucupera tu cuenta y comienza a publicar tus vacantes de manera gratis");

        return "auth/formOlvidePassword";
    }

    @GetMapping("/")
    public String formularioLogin(Model model) {
        model.addAttribute("nombrePagina", "Inicia Sesión en DevJobs");
        model.addAttribute("tagline", "Incia Sesión y Empieza Publicar tus Vacantes");

        return "auth/formularioLogin";
    }

    @GetMapping("/dev-jobs")
    public String devJobsSinSesion(Model model) {
        model.addAttribute("nombrePagina", "Vacantes Disponibles en DevJobs");
        model.addAttribute("tagline", "Registrate y Empieza a Publicar tus Vacantes");

        return "auth/devJobs";
    }

    @GetMapping("/dev-jobs/detalles/vacante/{id}")
    public String detallesVacanteAreaPublica(Model model) {
        model.addAttribute("nombrePagina", "Conoce la vacante");
        model.addAttribute("tagline", "Conoce la vacante");

        return "areaPublica/detallesVacante";
    }

    @GetMapping("/olvide-password/{token}")
    public String formRecuperarPassword(@PathVariable String token, Model model) {
        model.addAttribute("nombrePagina", "Recupera tu Password");
        model.addAttribute("tagline", "Ingresa tu nueva password y recupera tu cuenta de DevJobs");

        return "auth/formNuevaPassword";
    }

    @GetMapping("/confirmacion/{id}")
    public String formConfirmacionCuenta(Model model, @PathVariable String id) {
        model.addAttribute("nombrePagina", "Confirma tu Cuenta en DevJobs");
        model.addAttribute("tagline", "Confirma tu cuenta en DevJobs y comienza a publicar tus Vacantes de manera Gratis");

        return "auth/confirmacionCuenta";
    }
}
