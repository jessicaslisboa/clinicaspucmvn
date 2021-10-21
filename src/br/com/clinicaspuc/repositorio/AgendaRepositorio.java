package br.com.clinicaspuc.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.dao.AgendaDAO;
import br.com.clinicaspuc.model.Agenda;

@Stateless
public class AgendaRepositorio {

	@EJB
	private AgendaDAO agendaDAO;

	public List<Agenda> list() {
		return agendaDAO.findAll();
	}

	public Agenda salvar(Agenda agenda) {
		return agendaDAO.save(agenda);
	}

	public Agenda obterPorId(Integer codigo) {
		return agendaDAO.findById(codigo);
	}

	public void delete(Agenda a) {
		agendaDAO.remove(a);		
	}

	public List<Agenda> oberPorUsuario(int codUsuario) {
		Map<String, Object> params = new HashMap<>();
		params.put("codUsuario", codUsuario);
		return agendaDAO.findByNamedQueryAndParams("Agenda.obterPorUsuario",params );
	}
	
	public List<Agenda> obterAtivas() {
		return agendaDAO.findByNamedQuery("Agenda.obterAtivas");
	}
	
	

}	
