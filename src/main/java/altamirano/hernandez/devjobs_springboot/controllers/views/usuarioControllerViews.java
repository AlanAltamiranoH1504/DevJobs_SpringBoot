package altamirano.hernandez.devjobs_springboot.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class usuarioControllerViews {

    @GetMapping("/editar-perfil")
    public String formEditarPerfil(Model model) {
        model.addAttribute("nombrePagina", "Edita tu Perfil de Usuario");
        model.addAttribute("tagline", "Mantente Actualizado tu Perfil de Usuario");

        return "usuario/formEditarPerfil";
    }

    @GetMapping("/actualizar-foto")
    public String formActualizarFotoPerfil(Model model) {
        model.addAttribute("nombrePagina", "Edita tu Foto de Perfil de Usuario");
        model.addAttribute("tagline", "Mantente Actualizado tu Foto de Usuario");

        return "usuario/formActualizarFotoPerfil";
    }
}
