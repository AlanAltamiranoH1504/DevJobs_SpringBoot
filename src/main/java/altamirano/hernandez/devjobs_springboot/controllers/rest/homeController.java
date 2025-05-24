package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.models.DTO.UsuarioDTO;
import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.services.interfaces.ICandidatoService;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class homeController {
    @Autowired
    private IVacanteService iVacanteService;
    @Autowired
    private ICandidatoService iCandidatoService;

    @GetMapping("/findAllVacantes")
    public ResponseEntity<?> findAllVacantes(@CookieValue(name = "usuario_id") String usuario_id){
        Map<String, Object> json = new HashMap<String, Object>();
        try{
            Candidato candidato = iCandidatoService.findById(Integer.parseInt(usuario_id));
            UsuarioDTO usuarioDTO = new UsuarioDTO(candidato.getNombre(), candidato.getEmail(), candidato.getDescripcion(), candidato.getImgPerfil());
            List<Vacante> vacanteList = iVacanteService.findAllByUserId(Integer.parseInt(usuario_id));

            json.put("vacantes", vacanteList);
            json.put("usuario", usuarioDTO);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
