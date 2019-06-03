package tsti.desi.ejemplos.ejemplo02.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tsti.desi.ejemplos.ejemplo02.service.SprintCompletoException;
import tsti.desi.ejemplos.ejemplo02.service.TareaService;
import tsti.desi.ejemplos.ejemplo02.domain.Tarea;

/**
 * TareaController
 */
@RestController
@RequestMapping("/api")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Value("${mensajes.saludo}")
    private String saludo;

    @GetMapping("saludo")
    public String saludar() {
        return saludo;
    }

    @GetMapping("tarea")
    public List<Tarea> listar() {
        return this.tareaService.listar();
    }

    @PostMapping("tarea")
    public ResponseEntity<String> nueva(@RequestBody Tarea t) {
        if (t.getId() !=null &&  t.getId() >= 0)
            return ResponseEntity.badRequest().body("No puede crear una tarea con ID distinto de null");
        else {
            try {
				this.tareaService.agregarTarea(t);				
			} catch (SprintCompletoException e) {
				e.printStackTrace();
	            return ResponseEntity.badRequest().body(e.getMessage());
			}
            return ResponseEntity.ok().body("Creado OK");

        }
    }

    @PutMapping("tarea")
    public ResponseEntity<String> modificar(@RequestBody Tarea t) {
    	 try {
				this.tareaService.agregarTarea(t);				
			} catch (SprintCompletoException e) {
				e.printStackTrace();
	            return ResponseEntity.badRequest().body(e.getMessage());
			}
         return ResponseEntity.ok().body("Creado OK");
       }

    @DeleteMapping("tarea/{id}")
    public void borrar(@PathVariable Integer id) {
        Tarea t = new Tarea(id, "");
        this.tareaService.eliminar(t);
    }

    @GetMapping("tarea/{id}")
    public ResponseEntity<Tarea> buscar(@PathVariable Integer id) {
        Optional<Tarea> t = this.tareaService.listar().stream().filter(unaTarea -> unaTarea.getId().equals(id))
                .findFirst();
        if (!t.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(t.get());
        }

    }

    @GetMapping("tarea/")
    public ResponseEntity<Tarea> buscarIds(@RequestParam("idMinimo") Integer minimo,
            @RequestParam("idMaximo") Integer max) {
        Optional<Tarea> t = this.tareaService.listar().stream()
                .filter(unaTarea -> unaTarea.getId() > minimo && unaTarea.getId() < max).findFirst();
        if (!t.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(t.get());
        }

    }

    /**
     * @return the tareaService
     */
    public TareaService getTareaService() {
        return tareaService;
    }

    /**
     * @param tareaService the tareaService to set
     */
    public void setTareaService(TareaService tareaService) {
        this.tareaService = tareaService;
    }
}