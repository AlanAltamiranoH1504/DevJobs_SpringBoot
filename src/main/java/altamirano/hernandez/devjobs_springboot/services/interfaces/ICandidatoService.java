package altamirano.hernandez.devjobs_springboot.services.interfaces;

import altamirano.hernandez.devjobs_springboot.models.Candidato;

import java.util.List;

public interface ICandidatoService {
    public abstract List<Candidato> findAllCandidatos();
    public abstract Candidato findById(int id);
    public abstract void save(Candidato candidato);
    public abstract void deleteById(int id);
}
