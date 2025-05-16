package altamirano.hernandez.devjobs_springboot.services;

import altamirano.hernandez.devjobs_springboot.models.Rol;
import altamirano.hernandez.devjobs_springboot.repositories.IRolRepository;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplRolService implements IRolService {
    @Autowired
    private IRolRepository iRolRepository;
    @Override
    public List<Rol> findAll() {
        List<Rol> rolList = (List<Rol>) iRolRepository.findAll();
        return rolList;
    }

    @Override
    public Rol findById(int id) {
        try {
            Rol rol = iRolRepository.findById(id).get();
            return rol;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Rol rol) {
        try{
            iRolRepository.save(rol);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try{
            Rol rol = iRolRepository.findById(id).get();
            if (rol != null) {
                iRolRepository.deleteById(rol.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
