package br.com.clinicaspuc.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.model.Endereco;
import br.com.clinicaspuc.model.Paciente;
import br.com.clinicaspuc.repositorio.EnderecoRepositorio;
import br.com.clinicaspuc.repositorio.PacienteRepositorio;

@Stateless
public class PacienteService {

	@EJB
	private PacienteRepositorio pacienteRepositorio;
	
	@EJB
	private EnderecoRepositorio enderecoRepositorio;

	public List<Paciente> obterPacientes() {
		return pacienteRepositorio.consultaPacientes();
	}

	public Paciente salvar(Paciente paciente) {
		Paciente p = paciente;
		if (paciente.getEndereco() != null) {
			Endereco end = paciente.getEndereco();
			end = enderecoRepositorio.salvar(end);
			p.setEndereco(end);
		}
		p = pacienteRepositorio.salvar(p);
		return p;
	}
	
	public Paciente obterPorId(Integer codigo) {
		return pacienteRepositorio.obterPorId(codigo);
	}

	public void delete(Paciente p) {
		pacienteRepositorio.delete(p);		
	}

	
}	
