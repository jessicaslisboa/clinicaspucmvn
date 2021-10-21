package br.com.clinicaspuc.model;

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
@Table(name = "consulta", schema = "clinicaspuc")
@NamedQueries( value = { @NamedQuery(name="Consulta.obterPorPaciente", query="select c from Consulta c where c.paciente.codigo = :codPaciente" ),
				@NamedQuery(name="Consulta.obterNaoRealizadas", query="select c from Consulta c where c.realizada = false" )})
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_codigo_seq" )
	@SequenceGenerator(name = "consulta_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.consulta_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_paciente")
	private Paciente paciente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_agenda")
	private Agenda agenda;

	@Column(name = "realizada")
	private boolean realizada;
	
	@Column(name = "anotacao")
	private String anotacao;
	
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}


		
}
