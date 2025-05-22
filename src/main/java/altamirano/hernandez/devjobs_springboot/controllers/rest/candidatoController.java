package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.models.DTO.UsuarioDTO;
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
import org.springframework.web.bind.annotation.*;

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
        } else {
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

    @PostMapping("/findById")
    public ResponseEntity<?> findById(@CookieValue(name = "usuario_id") String usuario_id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Candidato candidato = iCandidatoService.findById(Integer.parseInt(usuario_id));
            UsuarioDTO usuarioDTO = new UsuarioDTO(candidato.getNombre(), candidato.getEmail(), candidato.getDescripcion(), candidato.getImgPerfil());
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
        } catch (Exception e) {
            json.put("msg", e.getMessage());
            json.put("line", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUsuario(@Valid @RequestBody Candidato candidato, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        } else {
            try {
                Candidato candidatoActul = iCandidatoService.findByEmail(candidato.getEmail());
                candidatoActul.setNombre(candidato.getNombre());
                candidatoActul.setEmail(candidato.getEmail());
                candidatoActul.setDescripcion(candidato.getDescripcion());
                candidatoActul.setPassword(passwordEncoder.encode(candidato.getPassword()));
                iCandidatoService.save(candidatoActul);
                json.put("msg", "Candidato actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } catch (Exception e) {
                json.put("msg", e.getMessage());
                json.put("line", e.getLocalizedMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }
}
