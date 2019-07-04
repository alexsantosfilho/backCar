package alocacao.exemplo.ws;

import javax.validation.constraints.NotNull;

public class MatriculaWs {

	private Long idMatricula;

	private Double valorMatricula;

	@NotNull(message = "obrigatório informar o usuário que está se matriculando!")
	private Long idUsuario;

	@NotNull(message = "obrigatório informar o curso em que o usuário está se matriculando!")
	private Long idCurso;

	public Long getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Double getValorMatricula() {
		return valorMatricula;
	}

	public void setValorMatricula(Double valorMatricula) {
		this.valorMatricula = valorMatricula;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

}
