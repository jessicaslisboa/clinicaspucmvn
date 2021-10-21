package br.com.clinicaspuc.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.model.Medicamento;
import br.com.clinicaspuc.repositorio.MedicamentoRepositorio;

@Stateless
public class MedicamentoService {

	@EJB
	private MedicamentoRepositorio medicamentoRepositorio;
	
	public List<Medicamento> obterMedicamentos() {
		return medicamentoRepositorio.consultaMedicamentos();
	}

	public Medicamento salvar(Medicamento medicamento) {
		Medicamento m = medicamentoRepositorio.salvar(medicamento);
		return m;
	}
	
	public Medicamento obterPorId(Integer codigo) {
		return medicamentoRepositorio.obterPorId(codigo);
	}

	public void delete(Medicamento m) {
		medicamentoRepositorio.delete(m);		
	}

	
}	
