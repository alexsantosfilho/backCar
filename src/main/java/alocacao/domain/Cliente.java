package alocacao.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Long idCliente;

	@Column(name = "nome", nullable = false, length = 100)
	@NotBlank(message = "O nome é obrigatório!")
	@Size(max = 100, message = "O nome deve conter no máximo 100 caracteres!")
	private String nome;

	@Column(name = "cpf", nullable = false, length = 14, unique = true)
	@NotBlank(message = "O CPF é obrigatório!")
	@Size(message = "O CPF deve estar no padrão 999.999.999-99", max = 14, min = 14)
	private String cpf;

	@Column(name = "rg", nullable = false, unique = true)
	@NotBlank(message = "O RG é obrigatório!")
	private String rg;

	@Column(name = "naturalidade", nullable = false)
	@NotBlank(message = "A naturalidade é obrigatória!")
	private String naturalidade;

	@Column(name = "datanascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@Column(name = "numCNH", nullable = false, unique = true)
	@NotNull(message = "O número da CNH é obrigatório")
	private int numCNH;

	@Column(name = "validadeCNH", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date validadeCNH;

	@Column(name = "tipoCNH", nullable = false)
	@NotBlank(message = "O tipo de Licença da CNH é obrigatório!")
	private String tipoCNH;

	@Column(name = "endereco", nullable = false)
	@NotBlank(message = "O Endereço é obrigatório e deve ser completo!")
	private String endereco;

	@Column(name = "telefone", nullable = false)
	@NotBlank(message = "O número do Telefone é obrigatório!")
	@Size(max = 13, min = 10, message = "numero invalido")
	private String telefone;

	@Column(name = "email", nullable = false, length = 100, unique = true)
	@NotBlank(message = "O E-mail é obrigatório!")
	@Size(max = 100, message = "O e-mail deve conter no máximo 100 caracteres!")
	@Email(message = "Formato de e-mail inválido!")
	private String email;

	@Column(name = "profissao", nullable = false)
	@NotBlank(message = "A Profissão é obrigatória!")
	private String profissao;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getNumCNH() {
		return numCNH;
	}

	public void setNumCNH(int numCNH) {
		this.numCNH = numCNH;
	}

	public Date getValidadeCNH() {
		return validadeCNH;
	}

	public void setValidadeCNH(Date validadeCNH) {
		this.validadeCNH = validadeCNH;
	}

	public String getTipoCNH() {
		return tipoCNH;
	}

	public void setTipoCNH(String tipoCNH) {
		this.tipoCNH = tipoCNH;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

}
