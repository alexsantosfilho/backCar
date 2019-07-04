package alocacao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import alocacao.domain.Veiculo;
import alocacao.projections.VeiculoProjection;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

	@Query(value = "SELECT v.placa AS placa FROM Veiculo v")
	List<VeiculoProjection> listarTodos();

	Optional<Veiculo> findByPlaca(String placa);

	@Query(value = "SELECT v FROM Veiculo v WHERE v.placa = :placa")
	Optional<Veiculo> buscarPorPlaca(@Param("placa") String placa);
}
