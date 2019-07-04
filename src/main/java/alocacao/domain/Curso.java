package alocacao.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurso")
	private Long idCurso;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "cargahoraria", nullable = false)
	private int cargaHoraria;

	@ManyToOne
	@JoinColumn(name = "idresponsavel")
	private Usuario responsavel;

	@OneToMany(mappedBy = "curso")
	@JsonIgnore
	private List<Matricula> matriculas;

	@ManyToMany
	@JoinTable(name = "cursodisciplina", joinColumns = @JoinColumn(name = "idcurso"), inverseJoinColumns = @JoinColumn(name = "iddisciplina"))
	private List<Disciplina> disciplinas;

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

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

}
