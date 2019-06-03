package tsti.desi.ejemplos.ejemplo02.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tsti.desi.ejemplos.ejemplo02.domain.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Integer>{
	
	@Query("SELECT SUM(t.storyPoints) FROM Sprint s INNER JOIN Tarea t WHERE s = :P_SPRINT")
	public Integer cantidadSP(@Param("P_SPRINT") Sprint s);
	
	@Query("SELECT COUNT(t) FROM Sprint s INNER JOIN Tarea t WHERE s = :P_SPRINT AND t.esUrgente = TRUE")
	public Integer cantidadUrgentes(@Param("P_SPRINT") Sprint s);

}
