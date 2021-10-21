package br.com.clinicaspuc.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.clinicaspuc.model.Perfil;
import br.com.clinicaspuc.model.Usuario;
import br.com.clinicaspuc.repositorio.UsuarioRepositorio;
import br.com.clinicaspuc.rs.dto.UsuarioLogin;

@Stateless
public class UsuarioService {

	@EJB
	private UsuarioRepositorio usuarioRepositorio;

	
	public List<Usuario> obterUsuarios() {
		return usuarioRepositorio.consultaUsuarios();
	}

	public Usuario salvar(Usuario usuario) {
		
		Perfil perfil = usuarioRepositorio.getPerfilPorId(usuario.getPerfil().getCodigo());
	
		Usuario user = usuario;
		user.setPerfil(perfil);
		user =  usuarioRepositorio.salvar(user);
		
		return user;
	}
	
	public Usuario obterPorId(Integer codigo) {
		return usuarioRepositorio.obterPorId(codigo);
	}

	public void delete(Usuario p) {
		usuarioRepositorio.delete(p);		
	}

	public List<Usuario> obterUsuariosPerfil(int codPerfil) {
		return usuarioRepositorio.obterPorPerfil(codPerfil);
	}

	public Boolean verificaLogin(UsuarioLogin usuario) {
		Usuario user = usuarioRepositorio.obterPorEmail(usuario.getEmail());
		if(user != null) {
			if(user.getSenha().equals(usuario.getSenha())){
				return true;
			}
		}
		return false;
	}

	public List<Perfil> getPerfilList() {
		return usuarioRepositorio.getPerfis();
	}
	
}	
