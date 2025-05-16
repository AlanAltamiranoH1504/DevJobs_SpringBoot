package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    //Relacion ManyToMany - Una skill puede estar asignada a varias vacantes
    @ManyToMany(mappedBy = "skills")
    private List<Vacante> vacantes;

    //Constructores
    public Skill() {}
    public Skill(String nombre) {
        this.nombre = nombre;
    }
    public Skill(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Get y Set
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

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id && Objects.equals(nombre, skill.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
