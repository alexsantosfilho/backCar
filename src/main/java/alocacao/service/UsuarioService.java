package alocacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alocacao.domain.Usuario;
import alocacao.exceptions.CustomRuntimeException;
import alocacao.projections.UsuarioProjection;
import alocacao.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarPorId(Long id) {
		if (id == null) {
			throw new CustomRuntimeException("id", "Não foi informado um ID para consulta!");
		}
		Optional<Usuario> usuario = this.usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.findByCpf(usuario.getCpf());
		if (usuarioOptional.isPresent()) {
			Usuario usuarioCpf = usuarioOptional.get();
			if (usuario.getIdUsuario() == null || usuario.getIdUsuario() != usuarioCpf.getIdUsuario()) {
				throw new CustomRuntimeException("cpf", "CPF cadastrado!");
			}
		}
		usuarioOptional = this.usuarioRepository.buscarPorEmail(usuario.getEmail());
		if (usuarioOptional.isPresent()) {
			Usuario usuarioEmail = usuarioOptional.get();
			if (usuario.getIdUsuario() == null || usuarioEmail.getIdUsuario() != usuario.getIdUsuario()) {
				throw new CustomRuntimeException("email", "E-mail cadastrado!");
			}
		}
		return this.usuarioRepository.save(usuario);
	}

	public Iterable<Usuario> buscarTodos() {
		return this.usuarioRepository.findAll();
	}

	public List<UsuarioProjection> listarTodos() {
		return this.usuarioRepository.listarTodos();
	}

}
