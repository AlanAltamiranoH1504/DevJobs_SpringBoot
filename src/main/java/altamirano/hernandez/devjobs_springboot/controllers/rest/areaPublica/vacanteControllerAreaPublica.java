package altamirano.hernandez.devjobs_springboot.controllers.rest.areaPublica;

import altamirano.hernandez.devjobs_springboot.models.Interesado;
import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vacantes/publica")
public class vacanteControllerAreaPublica {

    @Autowired
    private IVacanteService iVacanteService;

    @GetMapping("/prueba")
    public ResponseEntity<?> prueba() {
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "Funcionando prueba");
        return ResponseEntity.status(200).body(json);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findVacanteById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Vacante foundVacante = iVacanteService.findById(id);
            if (foundVacante != null) {
                return ResponseEntity.status(HttpStatus.OK).body(foundVacante);
            } else {
                json.put("msg", "Vacante no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/save/interesado")
    public ResponseEntity<?> saveInteresado(
            @RequestParam("cv") MultipartFile cv,
            @RequestParam("nombreInteresado") String nombreInteresado,
            @RequestParam("vacanteId") String vacanteId,
            @RequestParam("emailInteresado") String emailInteresado) {
        Map<String, Object> json = new HashMap<>();

        Interesado interesado = new Interesado(nombreInteresado, emailInteresado,"Sin cv");
        System.out.println("interesado = " + interesado);

        json.put("interesado", interesado);

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
