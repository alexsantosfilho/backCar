package alocacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alocacao.domain.Cliente;
import alocacao.exceptions.CustomRuntimeException;
import alocacao.projections.ClienteProjection;
import alocacao.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarPorId(Long id) {
		if (id == null) {
			throw new CustomRuntimeException("id", "Não foi informado um ID para consulta!");
		}
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		return null;
	}

	public Cliente salvar(Cliente cliente) {
		Optional<Cliente> clienteOptional = this.clienteRepository.findByCpf(cliente.getCpf());
		if (clienteOptional.isPresent()) {
			Cliente clienteCpf = clienteOptional.get();
			if (cliente.getIdCliente() == null || cliente.getIdCliente() != clienteCpf.getIdCliente()) {
				throw new CustomRuntimeException("cpf", "CPF já cadastrado!");
			}
		}
		clienteOptional = this.clienteRepository.buscarPorEmail(cliente.getEmail());
		if (clienteOptional.isPresent()) {
			Cliente clienteEmail = clienteOptional.get();
			if (cliente.getIdCliente() == null || clienteEmail.getIdCliente() != cliente.getIdCliente()) {
				throw new CustomRuntimeException("email", "E-mail já cadastrado!");
			}
		}
		return this.clienteRepository.save(cliente);
	}

	public Iterable<Cliente> buscarTodos() {
		return this.clienteRepository.findAll();
	}

	public List<ClienteProjection> listarTodos() {
		return this.clienteRepository.listarTodos();
	}

}
