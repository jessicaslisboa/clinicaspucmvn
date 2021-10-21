package br.com.clinicaspuc.repositorio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.dao.MedicamentoDAO;
import br.com.clinicaspuc.model.Medicamento;

@Stateless
public class MedicamentoRepositorio {

	@EJB
	private MedicamentoDAO medicamentoDAO;

	public List<Medicamento> consultaMedicamentos() {
		return medicamentoDAO.findAll();
	}

	public Medicamento salvar(Medicamento medicamento) {
		return medicamentoDAO.save(medicamento);
	}

	public Medicamento obterPorId(Integer codigo) {
		return medicamentoDAO.findById(codigo);
	}

	public void delete(Medicamento m) {
		medicamentoDAO.remove(m);		
	}

}	
