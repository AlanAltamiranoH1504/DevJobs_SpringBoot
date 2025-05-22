package altamirano.hernandez.devjobs_springboot.security;

//Clase de configuracion de security
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PutMapping;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    //Metodo authenticationManager
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Metodo BCryptPasswordEnode
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Metodo SecurityContextRepository para persistencia de sesion en AJAX
    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }

    //Metodo securityFilter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        //Rutas que no requieren proteccion
                        .requestMatchers(HttpMethod.POST, "/candidatos/save").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roles/save").permitAll()
                        .requestMatchers(HttpMethod.GET, "/crear-cuenta").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dev-jobs/**").permitAll()
                        .requestMatchers("/logout").permitAll()

                        //Rutas que requieren proteccion
                        .requestMatchers(HttpMethod.GET, "/home/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/home/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/vacante/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/vacante/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/candidatos/update").hasRole("USER")

                        //Liberacion de archivos estaticos
                        .requestMatchers("/css/**", "/assets/**", "/img/**", "/js/**", "/static/**").permitAll()

                        //Configuraciones generales
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/") //Ruta Formulario
                        .permitAll()
                )
                .logout(logout -> logout
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID", "usuario_id")
                );
        return http.build();
    }
}
