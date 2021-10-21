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

import br.com.clinicaspuc.model.Paciente;
import br.com.clinicaspuc.service.PacienteService;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteApi {
	
	@EJB
	private PacienteService pacienteService;
	
	@GET
	public Response getPaciente(){
		List<Paciente> paciente = null;
		try {
			paciente = pacienteService.obterPacientes();
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(paciente).build();
	}
	
	
	@GET
	@Path("{codPaciente}")
	public Response getPaciente(@PathParam("codPaciente") int codPaciente) {
		Paciente paciente = null;
		try {
			paciente = pacienteService.obterPorId(codPaciente);
		} catch (Exception e) {
			throw e;
		}
		if(paciente !=null) {
			return Response.ok(paciente).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}	
	
	@POST
	public Response salvar(Paciente paciente) {
		Paciente p = null;
		try {
			p = pacienteService.salvar(paciente);
		} catch (Exception e) {
			throw e;
		}
		
		return Response.ok(p).build();
		
	}
	
	@PUT
	@Path("{codPaciente}")
	public Response atualizar(@PathParam("codPaciente") int codigo, Paciente paciente) {
		Paciente p = null;
		try {
			p = pacienteService.obterPorId(codigo);
			if (p != null) {
				p = pacienteService.salvar(paciente);
			}
		} catch (Exception e) {
			throw e;
		}
		if(p !=null) {
			return Response.ok(p).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("/{codigo}")
	public Response excluir(@PathParam("codigo") Integer codigo) {
		Paciente p = pacienteService.obterPorId(codigo);
		if (p != null) {
			pacienteService.delete(p);
			return Response.ok().build();
		}else {
			//TODO Verificar status adequado
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
}
