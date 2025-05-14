package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vacantes")
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre de la vacante es obligatorio")
    @Size(min = 3, max = 80, message = "El nombre de la vacante tiene un maximo de 80 caracteres")
    private String titulo;

    @NotBlank(message = "El nombre de la empresa contratadora es obigatorio")
    @Size(max = 80, message = "El nombre de la empresa tiene un maximo de 80 caracteres")
    private String empresa;

    @NotBlank(message = "La ubicacion es obigatoria")
    @Size(min = 3, max = 80, message = "La ubicacion tiene un maximo de 80 caracteres")
    private String ubicacion;

    @NotBlank(message = "El salario es obligatorio")
    private String salario;

    @NotBlank(message = "El tipo de contrato es obligatorio")
    @Size(max = 80, message = "El tipo de contrato tiene un maximo de 80 caracteres")
    private String contrato;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 3, max = 500, message = "La descripcion tiene un maximo de 500 caracteres")
    private String descripcion;

    @NotBlank(message = "Las skills son obligatorias")
    private String skills;

    //Relacion ManyToMany: Una vacante puede tener varios candidatos
    @ManyToMany
    @JoinTable(
            name = "vacantes_candidatos",
            joinColumns = @JoinColumn(name = "vacante_id"),
            inverseJoinColumns = @JoinColumn(name = "candidato_id")
    )
    private List<Candidato> candidatos;

    //Constructores
    public Vacante(){

    }
    public Vacante(List<Candidato> candidatos, String titulo, String empresa, String descripcion, String contrato, String skills, String ubicacion, String salario) {
        this.candidatos = candidatos;
        this.titulo = titulo;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.contrato = contrato;
        this.skills = skills;
        this.ubicacion = ubicacion;
        this.salario = salario;
    }

    public Vacante(List<Candidato> candidatos, String contrato, String descripcion, String empresa, int id, String salario, String skills, String titulo, String ubicacion) {
        this.candidatos = candidatos;
        this.contrato = contrato;
        this.descripcion = descripcion;
        this.empresa = empresa;
        this.id = id;
        this.salario = salario;
        this.skills = skills;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
    }

    //Get y Set
    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public @NotBlank(message = "El tipo de contrato es obligatorio") @Size(max = 80, message = "El tipo de contrato tiene un maximo de 80 caracteres") String getContrato() {
        return contrato;
    }

    public void setContrato(@NotBlank(message = "El tipo de contrato es obligatorio") @Size(max = 80, message = "El tipo de contrato tiene un maximo de 80 caracteres") String contrato) {
        this.contrato = contrato;
    }

    public @NotBlank(message = "La descripcion es obligatoria") @Size(min = 3, max = 500, message = "La descripcion tiene un maximo de 500 caracteres") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "La descripcion es obligatoria") @Size(min = 3, max = 500, message = "La descripcion tiene un maximo de 500 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotBlank(message = "El nombre de la empresa contratadora es obigatorio") @Size(max = 80, message = "El nombre de la empresa tiene un maximo de 80 caracteres") String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(@NotBlank(message = "El nombre de la empresa contratadora es obigatorio") @Size(max = 80, message = "El nombre de la empresa tiene un maximo de 80 caracteres") String empresa) {
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "El salario es obligatorio") String getSalario() {
        return salario;
    }

    public void setSalario(@NotBlank(message = "El salario es obligatorio") String salario) {
        this.salario = salario;
    }

    public @NotBlank(message = "Las skills son obligatorias") String getSkills() {
        return skills;
    }

    public void setSkills(@NotBlank(message = "Las skills son obligatorias") String skills) {
        this.skills = skills;
    }

    public @NotBlank(message = "El nombre de la vacante es obligatorio") @Size(min = 3, max = 80, message = "El nombre de la vacante tiene un maximo de 80 caracteres") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El nombre de la vacante es obligatorio") @Size(min = 3, max = 80, message = "El nombre de la vacante tiene un maximo de 80 caracteres") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "La ubicacion es obigatoria") @Size(min = 3, max = 80, message = "La ubicacion tiene un maximo de 80 caracteres") String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(@NotBlank(message = "La ubicacion es obigatoria") @Size(min = 3, max = 80, message = "La ubicacion tiene un maximo de 80 caracteres") String ubicacion) {
        this.ubicacion = ubicacion;
    }

    //Equals y Hashcode

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacante vacante = (Vacante) o;
        return id == vacante.id && Objects.equals(titulo, vacante.titulo) && Objects.equals(empresa, vacante.empresa) && Objects.equals(ubicacion, vacante.ubicacion) && Objects.equals(salario, vacante.salario) && Objects.equals(contrato, vacante.contrato) && Objects.equals(descripcion, vacante.descripcion) && Objects.equals(skills, vacante.skills) && Objects.equals(candidatos, vacante.candidatos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, empresa, ubicacion, salario, contrato, descripcion, skills, candidatos);
    }
}
