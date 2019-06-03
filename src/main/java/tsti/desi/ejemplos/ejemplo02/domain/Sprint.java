package tsti.desi.ejemplos.ejemplo02.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sprint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	private Integer totalStoryPoints;
	@OneToMany(mappedBy = "sprint")
	private List<Tarea> tareas;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getTotalStoryPoints() {
		return totalStoryPoints;
	}
	public void setTotalStoryPoints(Integer totalStoryPoints) {
		this.totalStoryPoints = totalStoryPoints;
	}
	public List<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
}
