package alocacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alocacao.domain.Veiculo;
import alocacao.exceptions.CustomRuntimeException;

import alocacao.projections.VeiculoProjection;
import alocacao.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public Veiculo salvar(Veiculo veiculo) {
		Optional<Veiculo> veiculoOptional = this.veiculoRepository.findByPlaca(veiculo.getPlaca());
		if (veiculoOptional.isPresent()) {
			Veiculo veiculoPlaca = veiculoOptional.get();
			if (veiculo.getIdVeiculo() == null || veiculo.getIdVeiculo() != veiculoPlaca.getIdVeiculo()) {
				throw new CustomRuntimeException("placa", "Placa cadastrado!");
			}
		}

		veiculoOptional = this.veiculoRepository.buscarPorPlaca(veiculo.getPlaca());
		if (veiculoOptional.isPresent()) {
			Veiculo veiculoPlaca = veiculoOptional.get();
			if (veiculo.getIdVeiculo() == null || veiculoPlaca.getIdVeiculo() != veiculo.getIdVeiculo()) {
				throw new CustomRuntimeException("placa", "Placa cadastrada!");
			}
		}
		return this.veiculoRepository.save(veiculo);
	}

	public Veiculo buscarPorId(Long id) {
		if (id == null) {
			throw new CustomRuntimeException("id", "Não foi informado um ID");
		}
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);
		if (veiculo.isPresent()) {
			return veiculo.get();
		}
		return null;
	}

	public Iterable<Veiculo> buscarTodos() {
		return this.veiculoRepository.findAll();
	}

	public List<VeiculoProjection> listarTodos() {
		return this.veiculoRepository.listarTodos();
	}

}
