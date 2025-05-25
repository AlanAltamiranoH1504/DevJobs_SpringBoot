package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.models.Interesado;
import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.services.interfaces.ICandidatoService;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vacante")
public class vacanteController {

    @Autowired
    private IVacanteService iVacanteService;
    @Autowired
    private ICandidatoService iCandidatoService;

    @PostMapping("/save-vacante")
    public ResponseEntity<?> saveVacante(@Valid @RequestBody Vacante vacante, BindingResult bindingResult, @CookieValue(name = "usuario_id") String usuario_id) {
        Map<String, Object> json = new HashMap<>();
        System.out.println("usuario_id = " + usuario_id);
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->{
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else{
            try {
                Candidato candidato = iCandidatoService.findById(Integer.parseInt(usuario_id));
                vacante.setSkills(vacante.getSkills());
                vacante.setCandidato(candidato);
                System.out.println(vacante.getCandidato());
                System.out.println(vacante.getSkills());
                iVacanteService.save(vacante);
                json.put("msg", "Vacante guardada correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (Exception e) {
                json.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<?> findVacanteById(@PathVariable int id){
        Map<String, Object> json = new HashMap<>();
        try{
            Vacante foundVacante = iVacanteService.findById(id);
            if (foundVacante != null){
                return ResponseEntity.status(HttpStatus.OK).body(foundVacante);
            } else{
                json.put("msg", "Vacante no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateVacante(@Valid @RequestBody Vacante vacante, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->{
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else{
            try{
                iVacanteService.save(vacante);
                json.put("msg", "Vacante actualizada correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVacanteById(@PathVariable int id){
        Map<String, Object> json = new HashMap<>();
        try{
            iVacanteService.deleteById(id);
            json.put("msg", "Vacante eliminada");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getInteresados/{id}")
    public ResponseEntity<?> geteInteresados(@CookieValue(name = "usuario_id")String usuario_id, @PathVariable int id){
        Map<String, Object> json = new HashMap<>();

        //Busqueda de dueño de la vacante
        Vacante vacante = iVacanteService.findById(id);
        if (vacante.getCandidato().getId() != Integer.parseInt(usuario_id)) {
            json.put("msg", "Acceso no autorizado para esa vacante");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }

        try {
            List<Interesado> interesadosEnVacante = iVacanteService.getAllInteresados(id);
            return ResponseEntity.status(HttpStatus.OK).body(interesadosEnVacante);
        } catch (Exception e) {
            json.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}
