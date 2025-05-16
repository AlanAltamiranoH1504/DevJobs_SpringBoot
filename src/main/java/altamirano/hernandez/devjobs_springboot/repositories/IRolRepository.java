package altamirano.hernandez.devjobs_springboot.repositories;

import altamirano.hernandez.devjobs_springboot.models.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IRolRepository extends CrudRepository<Rol, Integer> {

    @Query("SELECT r FROM Rol r WHERE r.nombre =:nombre")
    public Rol rolByName(@Param("nombre") String nombre);
}
