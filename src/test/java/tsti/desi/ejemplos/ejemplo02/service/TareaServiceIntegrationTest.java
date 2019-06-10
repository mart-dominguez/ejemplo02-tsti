package tsti.desi.ejemplos.ejemplo02.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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

	@Ignore
	public void testNueva() {
		fail("Not yet implemented");
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
