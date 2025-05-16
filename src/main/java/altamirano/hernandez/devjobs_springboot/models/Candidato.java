package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "candidatos")
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre del candidato es obligatorio")
    @Size(max = 100, message = "El maximo del nombre son 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email de candidato es obligatorio")
    @Size(max = 100, message = "El maximo del email es 100 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, max = 100, message = "El minimo de la contraseña es 5 caracteres")
    private String password;

    @NotBlank(message = "El cv del candidato es obligatorio")
    @Size(max = 500)
    private String cv;

    //Relacion ManyToMany - Un candidato puede estar postulado a varias vacantes
    @ManyToMany(mappedBy = "candidatos")
    private List<Vacante> vacantes;

    //Constructores
    public Candidato() {}
    public Candidato(String nombre, String email, String password, String cv, List<Vacante> vacantes) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cv = cv;
        this.vacantes = vacantes;
    }
    public Candidato(int id, String nombre, String email, String password, String cv, List<Vacante> vacantes) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cv = cv;
        this.vacantes = vacantes;
    }

    //Metodos Get y Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre del candidato es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre del candidato es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El email de candidato es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El email de candidato es obligatorio") String email) {
        this.email = email;
    }

    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 5, max = 100, message = "El minimo de la contraseña es 5 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 5, max = 100, message = "El minimo de la contraseña es 5 caracteres") String password) {
        this.password = password;
    }

    public @NotBlank(message = "El cv del candidato es obligatorio") String getCv() {
        return cv;
    }

    public void setCv(@NotBlank(message = "El cv del candidato es obligatorio") String cv) {
        this.cv = cv;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return id == candidato.id && Objects.equals(nombre, candidato.nombre) && Objects.equals(email, candidato.email) && Objects.equals(password, candidato.password) && Objects.equals(cv, candidato.cv) && Objects.equals(vacantes, candidato.vacantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, password, cv, vacantes);
    }
}
