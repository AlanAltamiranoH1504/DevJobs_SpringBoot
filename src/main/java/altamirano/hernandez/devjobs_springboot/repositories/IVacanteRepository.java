package altamirano.hernandez.devjobs_springboot.repositories;

import altamirano.hernandez.devjobs_springboot.models.Vacante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVacanteRepository extends CrudRepository<Vacante, Integer> {

    @Query("SELECT v FROM Vacante v WHERE v.candidato.id =:id")
    List<Vacante> findAllByUserId(@Param("id") int id);
}
