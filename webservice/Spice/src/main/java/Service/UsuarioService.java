/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.UsuarioController;
import javax.ws.rs.Path;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Usuario;


@Path("/usuario")
public class UsuarioService {

    UsuarioController Controller = new UsuarioController();

    @GET
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsarios() {
        return Controller.getUsuario();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response saveJson(Usuario obj) {
    	
        try {
            System.out.println(obj.toString());
            //obj.setId(Controller.getCarro().size()+1 );
            Controller.addUsuario(obj);

            return Response.ok((Object) obj).build();
        } catch (Exception e) {
            e.printStackTrace();
            // return 404 to allow load balancers to only send traffic to the coordinator
            return Response.status(Response.Status.NOT_FOUND).build();
        }   

    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/acharUsuario")
    public Response acharUsuario(Usuario obj) {
        try {
            System.out.println(obj.toString());
            //obj.setId(Controller.getCarro().size()+1 );
            
            return Response.ok((Object)  Controller.acharCarro(obj)).build();
        } catch (Exception e) {
            e.printStackTrace();
            // return 404 to allow load balancers to only send traffic to the coordinator
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

   

}
