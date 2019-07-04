package alocacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alocacao.domain.Curso;
import alocacao.domain.Usuario;
import alocacao.exceptions.CustomRuntimeException;
import alocacao.exemplo.ws.CursoWs;
import alocacao.repositories.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private UsuarioService usuarioService;

	public Curso salvar(CursoWs cursoWs) {
		Curso curso = this.parseCursoWsToCurso(cursoWs);
		curso = this.cursoRepository.save(curso);
		return curso;
	}

	private Curso parseCursoWsToCurso(CursoWs cursoWs) {
		Usuario responsavel = this.obterResponsavel(cursoWs.getIdResponsavel());
		Curso curso = new Curso();
		curso.setCargaHoraria(cursoWs.getCargaHoraria());
		curso.setIdCurso(cursoWs.getIdCurso());
		curso.setNome(cursoWs.getNome());
		curso.setResponsavel(responsavel);
		return curso;
	}

	private Usuario obterResponsavel(Long idResponsavel) {
		Usuario responsavel = this.usuarioService.buscarPorId(idResponsavel);
		if (responsavel == null) {
			throw new CustomRuntimeException("responsavel", "É obrigatório definir um responsável para o curso!");
		}
		return responsavel;
	}

	public Curso buscarPorId(Long id) {
		if (id == null) {
			throw new CustomRuntimeException("curso", "Curso não encontrao!");
		}
		Optional<Curso> curso = this.cursoRepository.findById(id);
		if (curso.isPresent()) {
			return curso.get();
		}
		return null;
	}

	public Iterable<Curso> listarTodos() {
		return this.cursoRepository.findAll();
	}

	public List<Curso> listarPorCargaHorariaEntre(int inicio, int termino) {
		if (inicio > termino) {
			throw new CustomRuntimeException("cargaHoraria",
					"A carga horária de inicio deve ser maior que a de término!");
		}
		List<Curso> cursos = this.cursoRepository.listarPorCargaHorariaEntre(inicio, termino);
		return cursos;
	}

	public Curso obterCursoPorMatricula(Long idMatricula) {
		Optional<Curso> curso = this.cursoRepository.buscarCursoPorMatricula(idMatricula);
		if (curso.isPresent()) {
			return curso.get();
		}
		return null;
	}

}
