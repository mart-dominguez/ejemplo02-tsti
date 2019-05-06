package tsti.desi.ejemplos.ejemplo02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tsti.desi.ejemplos.ejemplo02.domain.Tarea;

/**
 * TareaService
 */
@Service
public class TareaService {

    private List<Tarea> listaTareas = new ArrayList<>();

    public List<Tarea> listar() {
        return listaTareas;
    }

    public void agregar(Tarea t) {
        this.listaTareas.add(t);
    }

    public void actualizar(Tarea t) {
        this.listaTareas.remove(t);
        this.listaTareas.add(t);
    }

    public void eliminar(Tarea t) {
        this.listaTareas.remove(t);
    }

}