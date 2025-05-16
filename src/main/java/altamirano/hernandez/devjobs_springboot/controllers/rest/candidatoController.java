package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.models.Rol;
import altamirano.hernandez.devjobs_springboot.repositories.IRolRepository;
import altamirano.hernandez.devjobs_springboot.services.interfaces.ICandidatoService;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IRolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidatos")
public class candidatoController {

    @Autowired
    private ICandidatoService iCandidatoService;
    @Autowired
    private IRolRepository iRolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public ResponseEntity<?> saveCandidato(@Valid @RequestBody Candidato candidato, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else{
            Rol rolUserDefault = iRolRepository.rolByName("USER");
            List<Rol> rolsDefaults = new ArrayList<>();
            rolsDefaults.add(rolUserDefault);

            candidato.setPassword(passwordEncoder.encode(candidato.getPassword()));
            candidato.setRoles(rolsDefaults);
            iCandidatoService.save(candidato);
            json.put("msg", "Candidato guardado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        }
    }
}
