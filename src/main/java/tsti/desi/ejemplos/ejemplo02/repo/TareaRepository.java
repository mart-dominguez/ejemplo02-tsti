package tsti.desi.ejemplos.ejemplo02.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tsti.desi.ejemplos.ejemplo02.domain.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
