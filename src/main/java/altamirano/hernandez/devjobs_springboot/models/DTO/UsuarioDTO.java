package altamirano.hernandez.devjobs_springboot.models.DTO;

import java.util.Objects;

public class UsuarioDTO {

    private String nombre;
    private String email;
    private String descripcion;
    private String imgPerfil;

    public UsuarioDTO() {}
    public UsuarioDTO(String nombre, String email, String descripcion, String imgPerfil) {
        this.nombre = nombre;
        this.email = email;
        this.descripcion = descripcion;
        this.imgPerfil = imgPerfil;
    }

    //Get y Set
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

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(descripcion, that.descripcion) && Objects.equals(imgPerfil, that.imgPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, email, descripcion, imgPerfil);
    }
}
