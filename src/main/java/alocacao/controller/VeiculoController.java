package alocacao.controller;

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
import alocacao.domain.Veiculo;
import alocacao.service.VeiculoService;

@RestController
@RequestMapping("/car-alocacao/veiculo")
@CrossOrigin
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	private MapValidationComponent mapValidationComponent;

	@GetMapping("/listar-todos")
	public ResponseEntity<?> listarTodos() {
		Iterable<Veiculo> veiculos = this.veiculoService.buscarTodos();
		return new ResponseEntity<Iterable<Veiculo>>(veiculos, HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody @Valid Veiculo veiculo, BindingResult result) {
		ResponseEntity<?> errors = this.mapValidationComponent.mapValidationError(result);
		if (errors != null) {
			return errors;
		}
		Veiculo veiculoSalvo = this.veiculoService.salvar(veiculo);
		return new ResponseEntity<Veiculo>(veiculoSalvo, HttpStatus.OK);
	}

	@GetMapping("/buscar-por-placa/{placa}")
	public ResponseEntity<?> buscarPorCPF(@PathVariable Long placa) {
		Veiculo veiculo = this.veiculoService.buscarPorId(placa);
		return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
	}

}
