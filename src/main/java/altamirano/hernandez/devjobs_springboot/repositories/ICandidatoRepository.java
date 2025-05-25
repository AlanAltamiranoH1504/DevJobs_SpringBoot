package altamirano.hernandez.devjobs_springboot.repositories;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICandidatoRepository extends CrudRepository<Candidato, Integer> {

    @Query("SELECT c FROM Candidato c WHERE c.email =:email")
    public Candidato findByEmail(@Param("email") String email);

    @Query("SELECT r.nombre FROM Candidato c JOIN c.roles r WHERE c.id =:id")
    public List<String> permisosByUserId(@Param("id") int id);

    @Query("SELECT c FROM Candidato c WHERE c.token =:token")
    public Candidato findByToken(@Param("token") String token);
}
