package tsti.desi.ejemplos.ejemplo02.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Tarea
 */
@Entity
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private Integer storyPoints;
    private Boolean esUrgente;
    @ManyToOne
    @JoinColumn(name="ID_SPRINT")
    private Sprint sprint;

    public Tarea() {
    		
    }
    
    public Tarea(Integer id, String descripcion) {
    	this(id,descripcion,0,false);
	}
    
    public Tarea(Integer id, String descripcion,Integer storyPoints) {
    	this(id,descripcion,storyPoints,false);
	}
    
    public Tarea(Integer id, String descripcion, Integer storyPoints, Boolean esUrgente) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.storyPoints = storyPoints;
		this.esUrgente = esUrgente;
	}

	/**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

	public Boolean getEsUrgente() {
		return esUrgente;
	}

	public void setEsUrgente(Boolean esUrgente) {
		this.esUrgente = esUrgente;
	}
	
	

	public Integer getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(Integer storyPoints) {
		this.storyPoints = storyPoints;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	@Override
    public boolean equals(Object obj) {
        return obj instanceof Tarea && ((Tarea) obj).id.equals(this.id);
    }

}