package tsti.desi.ejemplos.ejemplo02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;
import tsti.desi.ejemplos.ejemplo02.repo.SprintRepository;

/**
 * SprintService
 */
@Service
public class SprintService {

	private final SprintRepository sprintRepo;
	
	public SprintService(SprintRepository sprintRepo) {
		this.sprintRepo = sprintRepo;
	}

    public Sprint buscarPorId(Integer id) {
        return this.sprintRepo.findById(id).get();
    }
	
    public List<Sprint> listar() {
        return this.sprintRepo.findAll();
    }

    public void agregar(Sprint t) {
        this.sprintRepo.save(t);
    }

    public void eliminar(Sprint t) {
        this.sprintRepo.delete(t);
    }

    public Integer cantidadStoryPoints(Sprint s) {
    	return this.sprintRepo.cantidadSP(s);
    }
    
    public Integer cantidadUrgentes(Sprint s) {
    	return this.sprintRepo.cantidadUrgentes(s);
    }
    
}