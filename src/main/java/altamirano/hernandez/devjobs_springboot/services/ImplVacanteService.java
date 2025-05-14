package altamirano.hernandez.devjobs_springboot.services;

import altamirano.hernandez.devjobs_springboot.models.Vacante;
import altamirano.hernandez.devjobs_springboot.repositories.IVacanteRepository;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplVacanteService implements IVacanteService {
    @Autowired
    private IVacanteRepository iVacanteRepository;

    @Override
    public List<Vacante> findAllVacantes() {
        List<Vacante> vacanteList = (List<Vacante>) iVacanteRepository.findAll();
        return vacanteList;
    }

    @Override
    public Vacante findById(int id) {
        try{
            Vacante vacante = iVacanteRepository.findById(id).get();
            return vacante;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Vacante vacante) {
        try {
            iVacanteRepository.save(vacante);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            Vacante vacanteDelete = iVacanteRepository.findById(id).get();
            if (vacanteDelete != null){
                iVacanteRepository.delete(vacanteDelete);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
