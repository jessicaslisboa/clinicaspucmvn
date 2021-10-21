package br.com.clinicaspuc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historicoClinico", schema = "clinicaspuc")
public class HistoricoClinico {

	@Id
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	//private Paciente paciente;
	
	//private List<Exame> exames;
	
	private String anotacoes;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}
	
}
