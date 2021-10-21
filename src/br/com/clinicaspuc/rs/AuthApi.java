package br.com.clinicaspuc.rs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.clinicaspuc.rs.dto.UsuarioLogin;
import br.com.clinicaspuc.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthApi {
	
	@EJB
	private UsuarioService usuarioService;
	
	
	/*
	 * private final SecretKey CHAVE = Key. hmacShaKeyFor(
	 * "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
	 * .getBytes());
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UsuarioLogin usuario) {
		 {
		        try{
		        	if (usuarioService.verificaLogin(usuario)) {
		      
		                String jwtToken = Jwts.builder()
		                    .setSubject(usuario.getEmail())
		                    .setIssuer("localhost:8080")
		                    .setIssuedAt(new Date())
		                    .setExpiration(
								Date.from(
									LocalDateTime.now().plusMinutes(15L)
										.atZone(ZoneId.systemDefault())
									.toInstant()))
		                    .signWith(SignatureAlgorithm.RS512, "")
		                    .compact();
		                
		                return Response.status(Response.Status.OK).entity(jwtToken).build();
		            }
		            else
		                return Response.status(Response.Status.UNAUTHORIZED)
								.entity("Usuário e/ou senha inválidos").build();
		        }
		        catch(Exception ex)
		        {
		            return Response.status(
								Response.Status.INTERNAL_SERVER_ERROR
							).entity(ex.getMessage())
							.build();
		        } 
		    }
	
	}

}
