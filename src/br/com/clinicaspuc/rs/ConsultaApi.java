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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.clinicaspuc.model.Consulta;
import br.com.clinicaspuc.service.ConsultaService;

@Path("/consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaApi {
	
	@EJB
	private ConsultaService consultaService;
	
	@GET
	public Response getAll(){
		List<Consulta> consultas = null;
		try {
			consultas = consultaService.obterConsultas();
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(consultas).build();
	}
	
	@GET
	@Path("{codigoPaciente}")
	public Response getPorCodigo(@PathParam("codigoPaciente") int codigo) {
		Consulta consulta = null;
		try {
			consulta = consultaService.obterPorId(codigo);
		} catch (Exception e) {
			throw e;
		}
		if(consulta !=null) {
			return Response.ok(consulta).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}	
	
	@GET
	@Path("/pendentes")
	public Response getPorCodigoUsuario(){
		List<Consulta> consultas = null;
		try {
			consultas = consultaService.obterConsultasNaoRealizadas();
		} catch (Exception e) {
			throw e;
		}
		//TODO tratar usuario ?
		return Response.ok(consultas).build();
	}
	
	
	@GET
	@Path("/paciente/{codPaciente}")
	public Response getPorCodigoPaciente(@QueryParam("codPaciente") int codPaciente){
		List<Consulta> consultas = null;
		try {
			consultas = consultaService.oberPorPaciente(codPaciente);
		} catch (Exception e) {
			throw e;
		}
		//TODO tratar usuario ?
		return Response.ok(consultas).build();
	}
	
	@POST
	public Response salvar(Consulta consulta) {
		Consulta c = null;
		try {
			c = consultaService.salvar(consulta);
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(c).build();
	}
	
	@PUT
	public void atualizar(Consulta consulta) {
		
	}
	
	@DELETE
	@Path("/{codigo}")
	public void excluir(@PathParam("codigo") Integer codigo) {
		
	}
	
}
