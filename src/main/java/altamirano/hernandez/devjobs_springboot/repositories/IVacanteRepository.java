package altamirano.hernandez.devjobs_springboot.repositories;

import altamirano.hernandez.devjobs_springboot.models.Vacante;
import org.springframework.data.repository.CrudRepository;

public interface IVacanteRepository extends CrudRepository<Vacante, Integer> {
}
