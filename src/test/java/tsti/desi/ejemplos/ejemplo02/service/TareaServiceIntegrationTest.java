package tsti.desi.ejemplos.ejemplo02.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TareaServiceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;
    
    
    private static final String SERVER_URL = "http://localhost";
    		
    private String buildServerUrl(String path) {
    	return SERVER_URL+ ":" + this.randomServerPort+ "/api/" + path;
    }
    		
	@Test
	public void testListar() {
		//TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.
		  getForEntity(buildServerUrl("tarea"), String.class);
		 System.out.println(response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));	}

	@Test
	public void testNueva() {
		Sprint s1 = new Sprint();
		s1.setId(1);
		
		ResponseEntity<List<Tarea>> response = restTemplate.exchange(
				this.buildServerUrl("tarea"),
			  HttpMethod.GET,
			  null,
			  new ParameterizedTypeReference<List<Tarea>>(){});
			List<Tarea> tareas = response.getBody();
			int tamlista = tareas.size();
			System.out.println(tamlista );
			
			Tarea t = new Tarea();
		t.setDescripcion("tarea 99");
		t.setEsUrgente(false);
		t.setStoryPoints(15);
		t.setSprint(s1);
		HttpEntity<Tarea> request = new HttpEntity<>(t);
		String resp= restTemplate.postForObject(this.buildServerUrl("tarea"), request, String.class);
		assertNotNull(resp);
		assertEquals(resp, "Creado OK");
		
		ResponseEntity<List<Tarea>> response2 = restTemplate.exchange(
				this.buildServerUrl("tarea"),
			  HttpMethod.GET,
			  null,
			  new ParameterizedTypeReference<List<Tarea>>(){});
			List<Tarea> tareas2 = response2.getBody();
			int tamlista2 = tareas2.size();
			assertEquals(tamlista+1, tamlista2);
		
		
		
	}

	@Ignore
	public void testModificar() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testBorrar() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testBuscar() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testBuscarIds() {
		fail("Not yet implemented");
	}

}
