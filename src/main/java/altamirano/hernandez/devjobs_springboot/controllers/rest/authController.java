package altamirano.hernandez.devjobs_springboot.controllers.rest;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.models.Login;
import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.services.interfaces.ICandidatoService;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class authController {

    private static final Logger log = LoggerFactory.getLogger(authController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ICandidatoService iCandidatoService;
    @Autowired
    private IVacanteService iVacanteService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login login, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

            //Autenticacion Manual
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            //Creacion de cookie
            Candidato candidatoInSession = iCandidatoService.findByEmail(login.getEmail());
            Cookie cookie = new Cookie("usuario_id", Integer.toString(candidatoInSession.getId()));
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);

            json.put("status", HttpStatus.OK.value());

            return ResponseEntity.ok().body(json);
        } catch (BadCredentialsException e) {
            json.put("status", HttpStatus.UNAUTHORIZED.value());
            json.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getClass().getSimpleName());
            e.printStackTrace();
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            json.put("message", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllVacantes(){
        Map<String, Object> json = new HashMap<>();
        try{
            List<Vacante> vacantes = iVacanteService.findAllVacantes();
            return ResponseEntity.status(HttpStatus.OK).body(vacantes);
        } catch (Exception e) {
            json.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}
