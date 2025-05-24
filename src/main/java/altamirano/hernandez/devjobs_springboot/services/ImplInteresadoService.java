package altamirano.hernandez.devjobs_springboot.services;

import altamirano.hernandez.devjobs_springboot.models.Interesado;
import altamirano.hernandez.devjobs_springboot.repositories.IInteresadoRepository;
import altamirano.hernandez.devjobs_springboot.services.interfaces.IInteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplInteresadoService implements IInteresadoService {

    @Autowired
    private IInteresadoRepository iInteresadoRepository;

    @Override
    public List<Interesado> findAll() {
        List<Interesado> interesados = (List<Interesado>) iInteresadoRepository.findAll();
        return interesados;
    }

    @Override
    public Interesado findById(int id) {
        try {
            return iInteresadoRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Interesado interesado) {
        try{
            iInteresadoRepository.save(interesado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            iInteresadoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
