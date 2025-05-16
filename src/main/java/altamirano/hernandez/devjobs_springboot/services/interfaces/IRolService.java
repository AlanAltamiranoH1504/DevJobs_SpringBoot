package altamirano.hernandez.devjobs_springboot.services.interfaces;

import altamirano.hernandez.devjobs_springboot.models.Rol;

import java.util.List;

public interface IRolService {
    public abstract List<Rol> findAll();
    public abstract Rol findById(int id);
    public abstract void save(Rol rol);
    public abstract void deleteById(int id);
}
