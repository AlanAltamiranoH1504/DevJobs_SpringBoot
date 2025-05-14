package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vacante")
public class vacanteController {

    @Autowired
    private IVacanteService iVacanteService;

    @PostMapping("/save-vacante")
    public ResponseEntity<?> saveVacante(@Valid @RequestBody Vacante vacante, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->{
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else{
            try {
                iVacanteService.save(vacante);
                json.put("msg", "Vacante guardada correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (Exception e) {
                json.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }
}
