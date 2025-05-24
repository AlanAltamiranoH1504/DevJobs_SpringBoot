package altamirano.hernandez.devjobs_springboot.helpers;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneradorIDUnicos {

    public String generadorIdUnico(){
        String idUnico = UUID.randomUUID().toString();

        return idUnico;
    }
}
