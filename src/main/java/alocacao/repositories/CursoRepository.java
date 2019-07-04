package alocacao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import alocacao.domain.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

	@Query("SELECT c FROM Curso c " + "JOIN c.matriculas m " + "WHERE m.idMatricula = :idMatricula")
	public Optional<Curso> buscarCursoPorMatricula(@Param("idMatricula") Long idMatricula);

	@Query("SELECT c FROM Curso c WHERE c.cargaHoraria BETWEEN :inicio AND :termino")
	public List<Curso> listarPorCargaHorariaEntre(@Param("inicio") int inicio, @Param("termino") int termino);

}
