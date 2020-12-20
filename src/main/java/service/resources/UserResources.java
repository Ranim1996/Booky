package service.resources;

import service.ControllerPersistance.DataBookController;
import service.ControllerPersistance.DataUserController;
import service.model.Book;
import service.model.UserType;
import service.model.Users;
import service.model.Language;
import service.repository.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Path("/users")
public class UserResources {

    @Context
    private UriInfo uriInfo;
    // this has to be static because the service is stateless:
    DataUserController userController = new DataUserController();

    //return user with specific id
    @GET //GET at http://localhost:9090/booky/users/3
    @Path("{id}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPath(@PathParam("id") int id) {
        Users user = userController.getUser(id);

        if(user == null ){
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
        }
        else{
            return Response.ok(user).build();
        }
    }

    //update user personal infirmation using the user object
    @PUT //PUT at http://localhost:9090/booky/users/{id}
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @PermitAll
    public Response updateUser(@PathParam("id") int id, Users u) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (userController.updateUser(id, u)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid user ID.").build();
        }
    }

    //log into the web application using email and password

    @POST //POST at http://localhost:XXXX/users/
    @Path("login")
    @PermitAll
    @Produces("text/plain")
    public Response LoginUser(String body) {

        final StringTokenizer tokenizer = new StringTokenizer(body, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        Users user = userController.getUserByEmail(email);

        if (userController.login(email, password)) {
            String userId = Integer.toString(user.getId());
            String token = userController.createJWT(userId, user.getFirstName(),user.getUsertype().name(), -1);
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid email.").build();
        }
    }

    //add book with book object
    @POST //POST at http://localhost:9090/booky/users/
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createUser(Users user) {

        if (!userController.addUser(user)){
            String entity =  "User with this id: " + user.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        }
        else {
            System.out.println("User: " + user + user.getPassword());
            String url = uriInfo.getAbsolutePath() + "/" + user.getId(); // url of the posted user
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

}

