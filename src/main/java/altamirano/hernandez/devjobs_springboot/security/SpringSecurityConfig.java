package altamirano.hernandez.devjobs_springboot.security;

//Clase de configuracion de security

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    //Metodo securityFilter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //Rutas que no requieren proteccion
                        .requestMatchers(HttpMethod.POST, "/candidatos/save").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roles/save").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/**").permitAll()

                        //Rutas que requieren proteccion
                        .requestMatchers(HttpMethod.GET, "/home/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/home/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/vacante/**").hasRole("USER")

                        //Liberacion de archivos estaticos
                        .requestMatchers("/css/**", "/assets/**", "/img/**", "/js/**", "/static/**").permitAll()

                        //Configuraciones generales
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
