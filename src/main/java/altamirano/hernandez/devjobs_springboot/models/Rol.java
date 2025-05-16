package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El rol tener tener un tamaño de 3 a 100 caracteres")
    private String nombre;

    //Relacion ManyToMany con Candidato - Un rol puede pertencer a varios candidatos
    @ManyToMany(mappedBy = "roles")
    private List<Candidato> candidatos;

    //Constructores
    public Rol(){}
    public Rol(String nombre) {
        this.nombre = nombre;
    }
    public Rol(int id, String nombre) {
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

    public @NotBlank(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return id == rol.id && Objects.equals(nombre, rol.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
