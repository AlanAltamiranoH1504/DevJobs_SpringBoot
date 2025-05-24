package altamirano.hernandez.devjobs_springboot.services.interfaces;

import altamirano.hernandez.devjobs_springboot.models.Interesado;

import java.util.List;

public interface IInteresadoService {
    public List<Interesado> findAll();
    public Interesado findById(int id);
    public void save(Interesado interesado);
    public void deleteById(int id);
}
