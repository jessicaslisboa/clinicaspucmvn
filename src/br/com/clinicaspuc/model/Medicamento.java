package br.com.clinicaspuc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento", schema = "clinicaspuc")
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="medicamento_codigo_seq")
	@SequenceGenerator(name = "medicamento_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.medicamento_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "nome_generico")
	private String nomeGenerico;
	
	@Column(name = "fabricante")
	private String fabricante;
	
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

	public String getNomeGenerico() {
		return nomeGenerico;
	}

	public void setNomeGenerico(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
}
