package alocacao.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private Long idUsuario;

	@Column(name = "nome", nullable = false, length = 100)
	@NotBlank(message = "nome obrigatório!")
	@Size(max = 100, message = "nome máximo 100 caracteres!")
	private String nome;

	@Column(name = "email", nullable = false, length = 100, unique = true)
	@NotBlank(message = "e-mail é obrigatório")
	@Size(max = 100, message = "e-mail máximo 100 caracteres!")
	@Email(message = "Formato de e-mail inválido!")
	private String email;

	@Column(name = "cpf", nullable = false, length = 14, unique = true)
	@NotBlank(message = "CPF é obrigatório!")
	@Size(message = "CPF deve estar no padrão 999.999.999-99", max = 14, min = 14)
	private String cpf;

	@Column(name = "datanascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@Column(name = "descricao", nullable = true, columnDefinition = "text")
	private String descricao;

	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Matricula> matricula;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Matricula> getMatricula() {
		return matricula;
	}

	public void setMatricula(List<Matricula> matricula) {
		this.matricula = matricula;
	}

}
