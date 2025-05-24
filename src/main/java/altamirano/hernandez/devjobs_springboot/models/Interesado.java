package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "interesados")
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String email;
    private String cv;

    //Relacion ManyToMany - Un interesado puede estar postulado a varias vacantes
    @ManyToMany(mappedBy = "interesados")
    private List<Vacante> vacantes;

    public Interesado() {}
    public Interesado(String nombre, String email, String cv) {
        this.nombre = nombre;
        this.email = email;
        this.cv = cv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Interesado that = (Interesado) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(cv, that.cv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, cv);
    }
}
