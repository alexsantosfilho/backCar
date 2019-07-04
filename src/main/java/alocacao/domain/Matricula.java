package alocacao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "matricula")
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmatricula")
	private Long idMatricula;

	@Column(name = "datamatricula", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date dataMatricula;

	@Column(name = "valormatricula", nullable = true)
	private Double valorMatricula;

	@ManyToOne(optional = false)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	@ManyToOne(optional = true)
	@JoinColumn(name = "idcurso")
	private Curso curso;

	public Long getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Double getValorMatricula() {
		return valorMatricula;
	}

	public void setValorMatricula(Double valorMatricula) {
		this.valorMatricula = valorMatricula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}