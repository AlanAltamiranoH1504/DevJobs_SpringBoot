package altamirano.hernandez.devjobs_springboot.services.interfaces;

import altamirano.hernandez.devjobs_springboot.models.Interesado;
import altamirano.hernandez.devjobs_springboot.models.Vacante;

import java.util.List;

public interface IVacanteService {
    public abstract List<Vacante> findAllVacantes();
    public abstract List<Vacante> findAllByUserId(int id);
    public abstract Vacante findById(int id);
    public abstract List<Interesado> getAllInteresados(int id);
    public abstract void save(Vacante vacante);
    public abstract void deleteById(int id);
}
