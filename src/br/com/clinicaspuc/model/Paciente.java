package br.com.clinicaspuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "paciente", schema = "clinicaspuc")
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = 9108136410628584057L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_codigo_seq" )
	@SequenceGenerator(name = "paciente_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.paciente_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "dt_nascimento")
	private Date dataNascimento;

	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "sexo", length = 1)
	private String sexo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_endereco")
	private Endereco endereco;
	
	@Column(name = "email")
	private String email;
	
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
