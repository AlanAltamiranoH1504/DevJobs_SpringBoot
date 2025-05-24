package altamirano.hernandez.devjobs_springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Metodo de configuracion de archivos estaticos externos
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:statics/uploads/");
    }
}
