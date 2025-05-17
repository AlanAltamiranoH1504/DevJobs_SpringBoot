package altamirano.hernandez.devjobs_springboot.security;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.models.Rol;
import altamirano.hernandez.devjobs_springboot.services.interfaces.ICandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplUserDetailsService implements UserDetailsService {
    @Autowired
    private ICandidatoService iCandidatoService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Busqueda del usuario
        Candidato candidato = iCandidatoService.findByEmail(email);
        if (candidato == null) {
            throw new UsernameNotFoundException("Candidato con email: " + email + " no encontrado");
        }

        //Configuracion de permisos de usaurio
        List<GrantedAuthority> permisos = new ArrayList<>();
        List<String> permisosString = iCandidatoService.rolesByUserId(candidato.getId());
        for (var role: permisosString) {
            permisos.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        //Verificacion de permisos en el usaurio
        if (permisos.isEmpty()){
            throw new UsernameNotFoundException("El candidato con email: " + email + " no tiene roles asigandos");
        }
        System.out.println("PERMISOS DEL USAURIO CON EMAIL: " + email);
        System.out.println(permisos);

        //Retornamos usuario
        return new User(candidato.getEmail(), candidato.getPassword(), true, true, true, true, permisos);
    }
}
