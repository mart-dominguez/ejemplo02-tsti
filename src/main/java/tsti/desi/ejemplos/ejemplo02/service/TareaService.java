package tsti.desi.ejemplos.ejemplo02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;
import tsti.desi.ejemplos.ejemplo02.repo.SprintRepository;
import tsti.desi.ejemplos.ejemplo02.repo.TareaRepository;

/**
 * TareaService
 */
@Service
public class TareaService {

	private final TareaRepository tareaRepo;

	private final SprintService sprintService;
	
	public TareaService(SprintService sprintService,TareaRepository tareaRepo) {
		this.tareaRepo = tareaRepo;
		this.sprintService = sprintService;
	}

    public List<Tarea> listar() {
        return this.tareaRepo.findAll();
    }

    public void agregarTarea(Tarea t) throws SprintCompletoException {
    	// se puede agregar tareas al sprint si la cantidad de story points de la tarea
    	// no sobrepasa los story points del sprint y la tarea no es urgente
    	// o si la tarea es urgente los puede sobrepasar, pero solo pueden asignarse
    	// hasta 2 tareas urgentes al sprint
    	
    	Sprint s = this.sprintService.buscarPorId(t.getSprint().getId());
    	Integer spDisponibles = s.getTotalStoryPoints() - this.sprintService.cantidadStoryPoints(s);
    	Integer cantidadUrgentes = this.sprintService.cantidadUrgentes(s);
    	    	
    	if(spDisponibles>=t.getStoryPoints()  || (t.getEsUrgente() && cantidadUrgentes<2) || (t.getStoryPoints()-spDisponibles>2 && cantidadUrgentes==0)) {
    		t.setSprint(s);
    		this.tareaRepo.save(t);
    	} else {
    		throw new SprintCompletoException();
    	}
    }

    public void eliminar(Tarea t) {
        this.tareaRepo.delete(t);
    }

}