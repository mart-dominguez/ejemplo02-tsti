package tsti.desi.ejemplos.ejemplo02.service;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;

public class TareaServicePrototipo {

    public void agregarTarea(Tarea t) throws SprintCompletoException {
    	// se puede agregar tareas al sprint si la cantidad de story points de la tarea
    	// no sobrepasa los story points del sprint y la tarea no es urgente
    	// o si la tarea es urgente los puede sobrepasar, pero solo pueden asignarse
    	// hasta 2 tareas urgentes al sprint
    	
    	Sprint sp1 = t.getSprint();
    	if(sp1!=null) {
    		int maxSP = sp1.getTotalStoryPoints(); // cantidad de SP en el sprint
    		int cantidadAsignados = 0;
    		for(Tarea tAux : sp1.getTareas()) {
    			cantidadAsignados = cantidadAsignados + tAux.getStoryPoints();
    		}
    		
    		if(maxSP >= cantidadAsignados + t.getStoryPoints()){
    			sp1.getTareas().add(t);    			
    		} else {
    			throw new SprintCompletoException();
    		}    		
    	}
    }
}
