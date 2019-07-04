package alocacao.exemplo.ws;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CursoWs {

	private Long idCurso;

	@NotBlank(message = "nome do curso é obrigatório!")
	@Size(max = 150, message = "nome do curso no máximo 150 caracteres!")
	private String nome;

	@Min(value=1, message = "carga horária no mínimo 1 hora!")
	private int cargaHoraria;

	@NotNull(message = "obrigatório selecionar um responsável!")
	private Long idResponsavel;

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

}
