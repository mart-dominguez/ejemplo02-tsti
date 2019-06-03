package tsti.desi.ejemplos.ejemplo02.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;
import tsti.desi.ejemplos.ejemplo02.repo.TareaRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TareaServiceUnitTest {

	@MockBean
	SprintService sprintService;
	
	@MockBean
    TareaRepository tareaRepo;
	
    @Autowired TareaService tareaService;
	
	Tarea tareaNoUrgente;
	Tarea tareaUrgente;

	@Before
	public void iniciarTest() {
		tareaNoUrgente = new Tarea();
		tareaNoUrgente.setDescripcion("tarea 1");
		tareaNoUrgente.setEsUrgente(false);
		tareaNoUrgente.setStoryPoints(10);
		tareaUrgente = new Tarea();
		tareaUrgente.setDescripcion("tarea 2");
		tareaUrgente.setEsUrgente(true);
		tareaUrgente.setStoryPoints(10);
	}
	
	@Test
	public void testAgregarTareaConCapacidad() throws SprintCompletoException {		
		Sprint mockSprint = mock(Sprint.class);
		when(mockSprint.getTotalStoryPoints()).thenReturn(20);
		when(mockSprint.getId()).thenReturn(1);
        when(sprintService.buscarPorId(anyInt())).thenReturn(mockSprint);
        when(sprintService.cantidadStoryPoints(mockSprint)).thenReturn(5);
        when(sprintService.cantidadUrgentes(mockSprint)).thenReturn(0);

        tareaNoUrgente.setSprint(mockSprint);
        tareaService.agregarTarea(tareaNoUrgente);
        verify(tareaRepo).save(tareaNoUrgente);
	}
	
	@Test(expected=SprintCompletoException.class)
	public void testAgregarTareaSinCapacidad() throws SprintCompletoException {		
		Sprint mockSprint = mock(Sprint.class);
		when(mockSprint.getTotalStoryPoints()).thenReturn(20);
		when(mockSprint.getId()).thenReturn(1);
        when(sprintService.buscarPorId(anyInt())).thenReturn(mockSprint);
        when(sprintService.cantidadStoryPoints(mockSprint)).thenReturn(12);
        when(sprintService.cantidadUrgentes(mockSprint)).thenReturn(0);

        tareaNoUrgente.setSprint(mockSprint);
        tareaService.agregarTarea(tareaNoUrgente);
        verify(tareaRepo,never()).save(tareaNoUrgente);
	}
	
	@Test
	public void testAgregarTareaSinCapacidadUrgente() throws SprintCompletoException {		
		Sprint mockSprint = mock(Sprint.class);
		when(mockSprint.getTotalStoryPoints()).thenReturn(20);
		when(mockSprint.getId()).thenReturn(1);
        when(sprintService.buscarPorId(anyInt())).thenReturn(mockSprint);
        when(sprintService.cantidadStoryPoints(mockSprint)).thenReturn(18);
        when(sprintService.cantidadUrgentes(mockSprint)).thenReturn(0);

        tareaUrgente.setSprint(mockSprint);
        tareaService.agregarTarea(tareaUrgente);
        verify(tareaRepo).save(tareaUrgente);
	}

	@Test(expected=SprintCompletoException.class)
	public void testAgregarTareaSinCapacidadSinUrgente() throws SprintCompletoException {		
		Sprint mockSprint = mock(Sprint.class);
		when(mockSprint.getTotalStoryPoints()).thenReturn(20);
		when(mockSprint.getId()).thenReturn(1);
        when(sprintService.buscarPorId(anyInt())).thenReturn(mockSprint);
        when(sprintService.cantidadStoryPoints(mockSprint)).thenReturn(18);
        when(sprintService.cantidadUrgentes(mockSprint)).thenReturn(2);

        tareaUrgente.setSprint(mockSprint);
        tareaService.agregarTarea(tareaUrgente);
        verify(tareaRepo).save(tareaUrgente);
	}

}
