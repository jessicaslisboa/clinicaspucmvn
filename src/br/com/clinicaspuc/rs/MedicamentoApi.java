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

import br.com.clinicaspuc.model.Medicamento;
import br.com.clinicaspuc.service.MedicamentoService;

@Path("/medicamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicamentoApi {
	
	@EJB
	private MedicamentoService medicamentoService;
	
	@GET
	public Response getAll(){
		List<Medicamento> medicamentos = null;
		try {
			medicamentos = medicamentoService.obterMedicamentos();
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(medicamentos).build();
	}
	
	
	@GET
	@Path("{codigo}")
	public Response getPorCodigo(@PathParam("codigo") int codigo) {
		Medicamento medicamento = null;
		try {
			medicamento = medicamentoService.obterPorId(codigo);
		} catch (Exception e) {
			throw e;
		}
		if(medicamento !=null) {
			return Response.ok(medicamento).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}	
	
	@POST
	public Response salvar(Medicamento medicamento) {
		Medicamento p = null;
		try {
			p = medicamentoService.salvar(medicamento);
		} catch (Exception e) {
			throw e;
		}
		
		return Response.ok(p).build();
		
	}
	
	@PUT
	@Path("{codigo}")
	public Response atualizar(@PathParam("codigo") int codigo, Medicamento medicamento) {
		Medicamento m = null;
		try {
			m = medicamentoService.obterPorId(codigo);
			if (m != null) {
				m = medicamentoService.salvar(medicamento);
			}
		} catch (Exception e) {
			throw e;
		}
		if(m !=null) {
			return Response.ok(m).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("/{codigo}")
	public Response excluir(@PathParam("codigo") Integer codigo) {
		Medicamento m = medicamentoService.obterPorId(codigo);
		if (m != null) {
			medicamentoService.delete(m);
			return Response.ok().build();
		}else {
			//TODO Verificar status adequado
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
}
