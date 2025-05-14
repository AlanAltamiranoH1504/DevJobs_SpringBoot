package altamirano.hernandez.devjobs_springboot.repositories;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import org.springframework.data.repository.CrudRepository;

public interface ICandidatoRepository extends CrudRepository<Candidato, Integer> {
}
