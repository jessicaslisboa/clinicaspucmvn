package br.com.clinicaspuc.repositorio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.dao.EnderecoDAO;
import br.com.clinicaspuc.model.Endereco;

@Stateless
public class EnderecoRepositorio {

	@EJB
	private EnderecoDAO enderecoDAO;

	public Endereco salvar(Endereco endereco) {
		return enderecoDAO.save(endereco);
	}
}	
