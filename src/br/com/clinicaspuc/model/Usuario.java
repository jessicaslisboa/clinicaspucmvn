package br.com.clinicaspuc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", schema = "clinicaspuc")
@NamedQueries( value = { @NamedQuery(name="Usuario.obterPorPerfil", query="select u from Usuario u where u.perfil.codigo = :codPerfil" ),
			@NamedQuery(name = "Usuario.obterPorEmail", query = "select u from Usuario u where u.email = :email")})
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 9108136410628584057L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_codigo_seq" )
	@SequenceGenerator(name = "usuario_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.usuario_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_perfil")
	private Perfil perfil;
	
	@Column(name="senha")
	private String senha;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
