package altamirano.hernandez.devjobs_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    //Relacion ManyToMany: Una vacante puede tener varios interesados
    @ManyToMany
    @JoinTable(
            name = "vacantes_interesados",
            joinColumns = @JoinColumn(name = "vacante_id"),
            inverseJoinColumns = @JoinColumn(name = "interesado_id")
    )
    private List<Interesado> interesados;

    //Relacion ManyToOne - Varias vacantes para un candidato
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    Candidato candidato;

    //Relacion ManyToMany - Una vacante puede tener varias skill
    @NotEmpty(message = "Las skills son obligatorias")
    @ManyToMany
    @JoinTable(
            name = "vacantes_skills",
            joinColumns = @JoinColumn(name = "vacante_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    //Constructores
    public Vacante(){

    }
    public Vacante(List<Interesado> interesados, String titulo, String empresa, String descripcion, String contrato, List<Skill> skills, String ubicacion, String salario) {
        this.interesados = interesados;
        this.titulo = titulo;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.contrato = contrato;
        this.skills = skills;
        this.ubicacion = ubicacion;
        this.salario = salario;
    }

    public Vacante(List<Interesado> interesados, String contrato, String descripcion, String empresa, int id, String salario, List<Skill> skills, String titulo, String ubicacion) {
        this.interesados = interesados;
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
    public List<Interesado> getInteresados() {
        return interesados;
    }

    public void setInteresados(List<Interesado> interesados) {
        this.interesados = interesados;
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
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

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacante vacante = (Vacante) o;
        return id == vacante.id && Objects.equals(titulo, vacante.titulo) && Objects.equals(empresa, vacante.empresa) && Objects.equals(ubicacion, vacante.ubicacion) && Objects.equals(salario, vacante.salario) && Objects.equals(contrato, vacante.contrato) && Objects.equals(descripcion, vacante.descripcion) && Objects.equals(skills, vacante.skills) && Objects.equals(interesados, vacante.interesados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, empresa, ubicacion, salario, contrato, descripcion, skills, interesados);
    }
}
