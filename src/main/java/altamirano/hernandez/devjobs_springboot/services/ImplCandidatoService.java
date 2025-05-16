package altamirano.hernandez.devjobs_springboot.services;

import altamirano.hernandez.devjobs_springboot.models.Candidato;
import altamirano.hernandez.devjobs_springboot.repositories.ICandidatoRepository;
import altamirano.hernandez.devjobs_springboot.services.interfaces.ICandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplCandidatoService implements ICandidatoService {
    @Autowired
    private ICandidatoRepository iCandidatoRepository;

    @Override
    public List<Candidato> findAllCandidatos() {
        List<Candidato> candidatoList = (List<Candidato>) iCandidatoRepository.findAll();
        return candidatoList;
    }

    @Override
    public Candidato findById(int id) {
        try {
            Candidato candidato = iCandidatoRepository.findById(id).get();
            return candidato;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Candidato candidato) {
        try {
            iCandidatoRepository.save(candidato);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            Candidato candidato = iCandidatoRepository.findById(id).get();
            if (candidato != null) {
                iCandidatoRepository.deleteById(id);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
