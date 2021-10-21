package br.com.clinicaspuc.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.clinicaspuc.model.Perfil;
import br.com.clinicaspuc.model.Usuario;
import br.com.clinicaspuc.service.UsuarioService;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioApi {
	
	@EJB
	private UsuarioService usuarioService;
	
	@GET
	public Response getAll(){
		List<Usuario> usuario = null;
		try {
			usuario = usuarioService.obterUsuarios();
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(usuario).build();
	}
	
	
	@GET
	@Path("{codigo}")
	public Response getPorCodigo(@PathParam("codigo") int codigo) {
		Usuario usuario = null;
		try {
			usuario = usuarioService.obterPorId(codigo);
		} catch (Exception e) {
			throw e;
		}
		if(usuario !=null) {
			return Response.ok(usuario).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}	
	
	
	@GET
	@Path("/perfil")
	public Response listPerfil() {
		List<Perfil> perfis = null;
		try {
			perfis = usuarioService.getPerfilList();
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(perfis).build();
	}	
	
	@GET
	@Path("/perfil/{codigo}")
	public Response listUsuariosPorPerfil(@PathParam("codigo") int codigo) {
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioService.obterUsuariosPerfil(codigo);
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(usuarios).build();
	}	
	
	@POST
	public Response salvar(Usuario usuario) {
		Usuario u = null;
		try {
			u = usuarioService.salvar(usuario);
		} catch (Exception e) {
			throw e;
		}
		
		return Response.ok(u).build();
		
	}
	
	@PUT
	@Path("{codigo}")
	public Response atualizar(@PathParam("codigo") int codigo, Usuario usuario) {
		Usuario u = null;
		try {
			u = usuarioService.obterPorId(codigo);
			if (u != null) {
				u = usuarioService.salvar(usuario);
			}
		} catch (Exception e) {
			throw e;
		}
		if(u !=null) {
			return Response.ok(u).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("/{codigo}")
	public Response excluir(@PathParam("codigo") Integer codigo) {
		Usuario u = usuarioService.obterPorId(codigo);
		if (u != null) {
			usuarioService.delete(u);
			return Response.ok().build();
		}else {
			//TODO Verificar status adequado
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
}
