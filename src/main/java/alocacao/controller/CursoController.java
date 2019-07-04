package alocacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alocacao.components.MapValidationComponent;
import alocacao.domain.Curso;
import alocacao.exemplo.ws.CursoWs;
import alocacao.service.CursoService;

@RestController
@RequestMapping("/exemplo/curso")
public class CursoController {

	@Autowired
	private MapValidationComponent mapValidationComponent;

	@Autowired
	private CursoService cursoService;

	@GetMapping("/listar-todos")
	public ResponseEntity<?> listarTodos() {
		Iterable<Curso> cursos = this.cursoService.listarTodos();
		return new ResponseEntity<Iterable<Curso>>(cursos, HttpStatus.OK);
	}

	@GetMapping("/listar-por-carga-horaria-entre/{inicio}/{termino}")
	public ResponseEntity<?> listarPorCargaHorariaEntre(@PathVariable("inicio") int inicio,
			@PathVariable("termino") int termino) {
		List<Curso> cursos = this.cursoService.listarPorCargaHorariaEntre(inicio, termino);
		return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
	}

	@GetMapping("/buscar-curso-por-matricula/{idMatricula}")
	public ResponseEntity<?> buscarCursoPorMatricula(@PathVariable("idMatricula") Long idMatricula) {
		Curso curso = this.cursoService.obterCursoPorMatricula(idMatricula);
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody @Valid CursoWs cursoWs, BindingResult result) {
		ResponseEntity<?> error = this.mapValidationComponent.mapValidationError(result);
		if (error != null) {
			return error;
		}
		Curso curso = this.cursoService.salvar(cursoWs);
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

}
