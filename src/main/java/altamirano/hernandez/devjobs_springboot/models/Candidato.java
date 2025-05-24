package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
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

    @Size(min = 10, max = 500, message = "El tamaño maximo de la descripcion es de 500 caracteres")
    private String descripcion;

    @Size(max = 1000, message = "Maximo 500 caracteres")
    private String imgPerfil;

    @Size(max = 500)
    private String cv;

    //Relacion ManyToMany - Un candidato puede estar postulado a varias vacantes
    @ManyToMany(mappedBy = "candidatos")
    private List<Vacante> vacantes;

    //Relacion OneToMany - Un candidato para varias vacantes
    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Vacante> vacanteList = new ArrayList<>();

    //Relacion ManyToMany - Un candidato puede tener varios roles
    @ManyToMany
    @JoinTable(
            name = "roles_candidatos",
            joinColumns = @JoinColumn(name = "candidato_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roles;

    //Constructores
    public Candidato() {}
    public Candidato(String nombre, String email, String password, String descripcion, String imgPerfil, String cv, List<Vacante> vacantes, List<Rol> roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cv = cv;
        this.vacantes = vacantes;
        this.roles = roles;
        this.imgPerfil = imgPerfil;
        this.descripcion = descripcion;

    }
    public Candidato(int id, String nombre, String email, String password, String descripcion, String imgPerfil, String cv, List<Vacante> vacantes, List<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cv = cv;
        this.vacantes = vacantes;
        this.roles = roles;
        this.imgPerfil = imgPerfil;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return id == candidato.id && Objects.equals(nombre, candidato.nombre) && Objects.equals(email, candidato.email) && Objects.equals(password, candidato.password) && Objects.equals(descripcion, candidato.descripcion) && Objects.equals(imgPerfil, candidato.imgPerfil) && Objects.equals(cv, candidato.cv) && Objects.equals(vacantes, candidato.vacantes) && Objects.equals(vacanteList, candidato.vacanteList) && Objects.equals(roles, candidato.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, password, descripcion, imgPerfil, cv, vacantes, vacanteList, roles);
    }
}
