package tsti.desi.ejemplos.ejemplo02.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;

public class TareaServicePrototipoTest {

	private TareaServicePrototipo tareaSrv;
	private Sprint sp1;
	
	@Before
	public void setUp() throws Exception {
		this.tareaSrv = new TareaServicePrototipo();
		this.sp1 = new Sprint();
		this.sp1.setDescripcion("Sprint 1");
		this.sp1.setTotalStoryPoints(15);
		
		Tarea t0 = new Tarea();
		t0.setDescripcion("t0");
		t0.setEsUrgente(false);
		t0.setSprint(sp1);
		t0.setStoryPoints(10);
		
		sp1.setTareas(new ArrayList<>());
		sp1.getTareas().add(t0);
	}

	@Test
	public void testAgregarTarea() throws SprintCompletoException {
		Tarea t1 = new Tarea();
		t1.setSprint(sp1);
		t1.setStoryPoints(4);
		// cuantas tareas hay antes de agregar mi tarea
		int cantTareasSprintAntes = sp1.getTareas().size();
		
		// agregar tarea
		tareaSrv.agregarTarea(t1);
		
		// cuantas tareas hay despues
		int cantTareasSprintDespues = sp1.getTareas().size();
		
		assertEquals(cantTareasSprintAntes+1, cantTareasSprintDespues);		
	}

	@Test
	public void testAgregarConSprintNull() throws SprintCompletoException {
		Tarea t1 = new Tarea();
		t1.setSprint(null);
		t1.setStoryPoints(4);
		// cuantas tareas hay antes de agregar mi tarea
		int cantTareasSprintAntes = sp1.getTareas().size();
		
		// agregar tarea
		tareaSrv.agregarTarea(t1);
		
		// cuantas tareas hay despues
		int cantTareasSprintDespues = sp1.getTareas().size();
		
		assertEquals(cantTareasSprintAntes, cantTareasSprintDespues);		
	}
	
	@Test(expected = SprintCompletoException.class)
	public void testAgregarConSPMayor() throws SprintCompletoException {
		Tarea t1 = new Tarea();
		t1.setSprint(sp1);
		t1.setStoryPoints(8);
		// cuantas tareas hay antes de agregar mi tarea
		int cantTareasSprintAntes = sp1.getTareas().size();
		
		// agregar tarea
		tareaSrv.agregarTarea(t1);
		
		// cuantas tareas hay despues
		int cantTareasSprintDespues = sp1.getTareas().size();
		
		assertEquals(cantTareasSprintAntes, cantTareasSprintDespues);		
	}

}
