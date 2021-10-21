package br.com.clinicaspuc.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.dao.ConsultaDAO;
import br.com.clinicaspuc.model.Consulta;

@Stateless
public class ConsultaRepositorio {

	@EJB
	private ConsultaDAO consultaDAO;

	public List<Consulta> list() {
		return consultaDAO.findAll();
	}

	public Consulta salvar(Consulta consulta) {
		return consultaDAO.save(consulta);
	}

	public Consulta obterPorId(Integer codigo) {
		return consultaDAO.findById(codigo);
	}

	public void delete(Consulta a) {
		consultaDAO.remove(a);		
	}

	public List<Consulta> oberPorPaciente(int codPaciente) {
		Map<String, Object> params = new HashMap<>();
		params.put("codPaciente", codPaciente);
		return consultaDAO.findByNamedQueryAndParams("Consulta.obterPorPaciente",params );
	}
	
	public List<Consulta> obterNaoRealizadas() {
		return consultaDAO.findByNamedQuery("Consulta.obterNaoRealizadas");
	}
	
	

}	
