package br.com.clinicaspuc.repositorio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.dao.PacienteDAO;
import br.com.clinicaspuc.model.Paciente;

@Stateless
public class PacienteRepositorio {

	@EJB
	private PacienteDAO pacienteDAO;

	public List<Paciente> consultaPacientes() {
		return pacienteDAO.findAll();
	}

	public Paciente salvar(Paciente paciente) {
		return pacienteDAO.save(paciente);
	}

	public Paciente obterPorId(Integer codigo) {
		return pacienteDAO.findById(codigo);
	}

	public void delete(Paciente p) {
		pacienteDAO.remove(p);		
	}

}	
