package br.com.clinicaspuc.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.model.Agenda;
import br.com.clinicaspuc.repositorio.AgendaRepositorio;

@Stateless
public class AgendaService {

	@EJB
	private AgendaRepositorio agendaRepositorio;

	
	public List<Agenda> obterAgendas() {
		return agendaRepositorio.obterAtivas();
	}

	public Agenda salvar(Agenda agenda) {
		Agenda a = agendaRepositorio.salvar(agenda);
		return a;
	
	}
	
	public Agenda obterPorId(Integer codigo) {
		return agendaRepositorio.obterPorId(codigo);
	}

	public void delete(Agenda a) {
		agendaRepositorio.delete(a);		
	}

	public List<Agenda> oberAgendaPorUsuario(int codUsuario) {
		return agendaRepositorio.oberPorUsuario(codUsuario);
	}

}	
