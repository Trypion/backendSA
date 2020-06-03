
package Service;

import Controller.ItemController;
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

import model.Item;

@Path("/item")
public class ItemService {

	static ItemController Controller = new ItemController();
	
	//rota para todos os Items
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllItem() {
		try {
			ArrayList<Item> retorno = Controller.getItem();
			return Response.ok((ArrayList<Item>) retorno).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	//rota para um unico Item
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getItem(@PathParam("id") int id) {
		try {
			Item retorno = Controller.acharItem(id);
			return Response.ok((Item) retorno).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	//rota para registrar um Item
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveJson(Item obj) {
		try {
			System.out.println(obj.toString());

			if(Controller.addItem(obj)) {
				return Response.ok((Object) obj).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			// return 404 to allow load balancers to only send traffic to the coordinator
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}

	//rota para atualizar um Item
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response saveJsonPut(@PathParam("id") int id, Item obj) {
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

	//rota para deletar um Item
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
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
