package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/findAllVacantes")
    public ResponseEntity<?> findAllVacantes(){
        Map<String, Object> json = new HashMap<String, Object>();
        try{
            List<Vacante> vacanteList = iVacanteService.findAllVacantes();
            json.put("vacantes", vacanteList);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
