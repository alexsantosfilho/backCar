package alocacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alocacao.components.MapValidationComponent;
import alocacao.domain.Usuario;
import alocacao.projections.UsuarioProjection;
import alocacao.service.UsuarioService;

@RestController
@RequestMapping("/exemplo/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MapValidationComponent mapValidationComponent;

	@GetMapping("/listar-todos")
	public ResponseEntity<?> listarTodos() {
		Iterable<Usuario> usuarios = this.usuarioService.buscarTodos();
		return new ResponseEntity<Iterable<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		List<UsuarioProjection> usuarios = this.usuarioService.listar();
		return new ResponseEntity<List<UsuarioProjection>>(usuarios, HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody @Valid Usuario usuario, BindingResult result) {
		ResponseEntity<?> errors = this.mapValidationComponent.mapValidationError(result);
		if (errors != null) {
			return errors;
		}
		Usuario usuarioSalvo = this.usuarioService.salvar(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	@GetMapping("/buscar-por-id/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.buscarPorId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

}
