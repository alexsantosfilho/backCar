package alocacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alocacao.components.MapValidationComponent;
import alocacao.domain.Matricula;
import alocacao.exemplo.ws.MatriculaWs;
import alocacao.service.MatriculaService;

@RestController
@RequestMapping("/exemplo/matricula")
public class MatriculaController {

	@Autowired
	private MatriculaService matriculaService;

	@Autowired
	private MapValidationComponent mapValidationComponent;

	@GetMapping("/listar-paginado/{page}/{size}")
	public ResponseEntity<?> listarPaginado(@PathVariable("page") int page, @PathVariable("size") int size) {
		List<Matricula> matriculas = this.matriculaService.listarMatriculasPaginado(page, size);
		return new ResponseEntity<List<Matricula>>(matriculas, HttpStatus.OK);
	}

	@GetMapping("/buscar-por-usuario/{idUsuario}")
	public ResponseEntity<?> buscarPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
		List<Matricula> matriculas = this.matriculaService.buscarMatriculasPorUsuario(idUsuario);
		return new ResponseEntity<List<Matricula>>(matriculas, HttpStatus.OK);
	}

	@DeleteMapping("/remover/{id}")
	public ResponseEntity<?> removerPorId(@PathVariable("id") Long id) {
		this.matriculaService.remover(id);
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody @Valid MatriculaWs matriculaWs, BindingResult result) {
		ResponseEntity<?> error = this.mapValidationComponent.mapValidationError(result);
		if (error != null) {
			return error;
		}
		Matricula matricula = this.matriculaService.salvar(matriculaWs);
		return new ResponseEntity<Matricula>(matricula, HttpStatus.OK);
	}

}
