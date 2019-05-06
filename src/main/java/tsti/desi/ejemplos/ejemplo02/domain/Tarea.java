package tsti.desi.ejemplos.ejemplo02.domain;

/**
 * Tarea
 */
public class Tarea {

    public Tarea() {

    }

    public Tarea(Integer id, String d) {
        this.id = id;
        this.descripcion = d;
    }

    private Integer id;
    private String descripcion;

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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tarea && ((Tarea) obj).id.equals(this.id);
    }

}