package alocacao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import alocacao.domain.Cliente;
import alocacao.projections.ClienteProjection;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Optional<Cliente> findByCpf(String cpf);

	@Query(value = "SELECT u FROM Cliente u WHERE u.email = :email")
	Optional<Cliente> buscarPorEmail(@Param("email") String email);

	@Query(value = "SELECT u.nome AS nome, u.email AS email FROM Cliente u")
	List<ClienteProjection> listarTodos();

}
