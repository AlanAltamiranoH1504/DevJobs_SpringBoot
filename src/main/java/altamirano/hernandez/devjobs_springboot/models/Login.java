package altamirano.hernandez.devjobs_springboot.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Login {
    @NotBlank(message = "El email es obligatorio para iniciar sesión")
    private String email;
    @NotBlank(message = "La password es obligatoria para iniciar sesion")
    private String password;

    //Constructores
    public Login(){
    }
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Get y Set
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(email, login.email) && Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
