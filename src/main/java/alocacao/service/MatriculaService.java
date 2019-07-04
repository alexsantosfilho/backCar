package alocacao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import alocacao.domain.Curso;
import alocacao.domain.Matricula;
import alocacao.domain.Usuario;
import alocacao.exceptions.CustomRuntimeException;
import alocacao.exemplo.ws.MatriculaWs;
import alocacao.repositories.MatriculaRepository;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CursoService cursoService;

	public Matricula salvar(MatriculaWs matriculaWs) {
		Matricula matricula = this.parseMatriculaWsToMatricula(matriculaWs);
		matricula = this.matriculaRepository.save(matricula);
		return matricula;
	}

	public List<Matricula> buscarMatriculasPorUsuario(Long idUsuario) {
		if (idUsuario == null) {
			throw new CustomRuntimeException("usuario", "obrigatório informar o usuário");
		}
		List<Matricula> matriculas = this.matriculaRepository.buscarMatriculasPorUsuario(idUsuario);
		return matriculas;
	}

	private Usuario obterUsuario(Long idUsuario) {
		Usuario usuario = this.usuarioService.buscarPorId(idUsuario);
		if (usuario == null) {
			throw new CustomRuntimeException("usuario", "Usuário não encontrado!");
		}
		return usuario;
	}

	private Curso obterCurso(Long idCurso) {
		Curso curso = this.cursoService.buscarPorId(idCurso);
		if (curso == null) {
			throw new CustomRuntimeException("curso", "Curso não encontrado!");
		}
		return curso;
	}

	private Matricula parseMatriculaWsToMatricula(MatriculaWs matriculaWs) {
		Usuario usuario = this.obterUsuario(matriculaWs.getIdUsuario());
		Curso curso = this.obterCurso(matriculaWs.getIdCurso());
		Matricula matricula = new Matricula();
		matricula.setDataMatricula(new Date());
		matricula.setIdMatricula(matriculaWs.getIdMatricula());
		matricula.setUsuario(usuario);
		matricula.setValorMatricula(matriculaWs.getValorMatricula());
		matricula.setCurso(curso);
		return matricula;
	}

	public List<Matricula> listarMatriculasPaginado(int page, int size) {
		return this.matriculaRepository.listarMatriculasPaginado(PageRequest.of(page, size));
	}

	public void remover(Long id) {
		this.matriculaRepository.deleteById(id);
	}

}
