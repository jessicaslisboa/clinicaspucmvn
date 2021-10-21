package br.com.clinicaspuc.model;

import java.util.Date;

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
@Table(name = "agenda", schema = "clinicaspuc")
@NamedQueries( value = { @NamedQuery(name="Agenda.obterPorUsuario", 
					query="select a from Agenda a where a.usuario.codigo = :codUsuario" ),
		@NamedQuery(name="Agenda.obterAtivas", 
		query="select a from Agenda a where a.disponivel = true" )})
public class Agenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agenda_codigo_seq")
	@SequenceGenerator(name = "agenda_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.agenda_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_usuario")
	private Usuario usuario;
	
	@Column(name="data")
	private Date data;
	
	@Column(name = "disponivel")
	private Boolean disponivel;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
