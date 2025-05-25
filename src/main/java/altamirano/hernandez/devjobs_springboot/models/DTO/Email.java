package altamirano.hernandez.devjobs_springboot.models.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Email {

    @NotBlank(message = "El correo destino no puede estar vacio")
    private String destinatario;
    private String asunto;
    private String mensaje;

    //Constructores
    public Email() {}

    public Email(String destinatario){
        this.destinatario = destinatario;
    }

    public Email(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    //Get y Set
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    //E y H
    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(destinatario, email.destinatario) && Objects.equals(asunto, email.asunto) && Objects.equals(mensaje, email.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinatario, asunto, mensaje);
    }
}
