
package service;

import javax.ws.rs.Path;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.PerfilController;
import model.Perfil;

@Path("/perfil")
public class PerfilService {

	static PerfilController Controller = new PerfilController();

	// rota para todos os perfils
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllperfil() {
		try {
			ArrayList<Perfil> retorno = Controller.getPerfil();
			return Response.ok((ArrayList<Perfil>) retorno).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	// rota para um unico perfil
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getPerfil(@PathParam("id") String id) {
		try {
			Perfil retorno = Controller.acharPerfil(id);
			return Response.ok((Perfil) retorno).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	// rota para registrar um perfil
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveJson(Perfil obj) {
		try {
			System.out.println(obj.toString());

			if (Controller.addPerfil(obj)) {
				return Response.ok((Object) obj).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			// return 404 to allow load balancers to only send traffic to the coordinator
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}

	// rota para atualizar um perfil
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response saveJsonPut(@PathParam("id") String id, Perfil obj) {
		try {
			if (Controller.update(id, obj)) {
				obj.setId(id);
				return Response.ok((Object) obj).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			// return 404 to allow load balancers to only send traffic to the coordinator
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	// rota para deletar um perfil
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		try {
			if (Controller.delete(id)) {
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// return 404 to allow load balancers to only send traffic to the coordinator
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
