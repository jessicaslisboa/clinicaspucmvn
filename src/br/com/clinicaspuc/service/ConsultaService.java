package br.com.clinicaspuc.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.model.Consulta;
import br.com.clinicaspuc.repositorio.ConsultaRepositorio;

@Stateless
public class ConsultaService {

	@EJB
	private ConsultaRepositorio consultaRepositorio;

	
	public List<Consulta> obterConsultas() {
		return consultaRepositorio.list();
	}

	public Consulta salvar(Consulta consulta) {
		Consulta c = consultaRepositorio.salvar(consulta);
		return c;
	
	}
	
	public Consulta obterPorId(Integer codigo) {
		return consultaRepositorio.obterPorId(codigo);
	}

	public void delete(Consulta c) {
		consultaRepositorio.delete(c);		
	}

	public List<Consulta> oberPorPaciente(int codPaciente) {
		return consultaRepositorio.oberPorPaciente(codPaciente);
	}
	
	public List<Consulta> obterConsultasNaoRealizadas(){
		return consultaRepositorio.obterNaoRealizadas();
	}

}	
