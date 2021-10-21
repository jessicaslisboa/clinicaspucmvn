package br.com.clinicaspuc.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.dao.PerfilDAO;
import br.com.clinicaspuc.dao.UsuarioDAO;
import br.com.clinicaspuc.model.Perfil;
import br.com.clinicaspuc.model.Usuario;

@Stateless
public class UsuarioRepositorio {

	@EJB
	private UsuarioDAO usuarioDAO;
	
	@EJB
	private PerfilDAO perfilDAO;

	public List<Usuario> consultaUsuarios() {
		return usuarioDAO.findAll();
	}

	public Usuario salvar(Usuario usuario) {
		return usuarioDAO.save(usuario);
	}

	public Usuario obterPorId(Integer codigo) {
		return usuarioDAO.findById(codigo);
	}

	public void delete(Usuario p) {
		usuarioDAO.remove(p);		
	}

	public List<Usuario> obterPorPerfil(int codPerfil) {
		Map<String, Object> params = new HashMap<>();
		params.put("codPerfil", codPerfil);
		return usuarioDAO.findByNamedQueryAndParams("Usuario.obterPorPerfil",params );
	}
	
	public Usuario obterPorEmail(String email) {
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		return usuarioDAO.findOneByNamedQueryAndParams("Usuario.obterPorEmail",params );
	}

	public List<Perfil> getPerfis() {
		return perfilDAO.findAll();
	}

	public Perfil getPerfilPorId(Integer codigo) {
		return perfilDAO.findById(codigo);
	}
}	

