package alocacao.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import alocacao.domain.Matricula;

public interface MatriculaRepository extends CrudRepository<Matricula, Long> {

	@Query("SELECT m FROM Matricula m " + "JOIN m.usuario u " + "WHERE u.idUsuario = :idUsuario")
	List<Matricula> buscarMatriculasPorUsuario(@Param("idUsuario") Long idUsuario);

	@Query("SELECT m FROM Matricula m")
	List<Matricula> listarMatriculasPaginado(Pageable pageable);

}
