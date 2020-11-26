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
    public Response getUserPath(@PathParam("id") int id, @HeaderParam("Authorization") String auth) {

        System.out.println("Authentication get User Path: " + auth);

        if (!userController.isIdAndAuthSame(id, auth)){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email and/or password.").build();
        }
        else{
            Users u = userController.showUserById(id);
            if(userController.showUserById(id) != null){
                return Response.ok(u).build();
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid id.").build();
            }
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

    //http headers like the demo implement it with the headers
    @POST
    @Path("login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response LoginUser(String body, @HeaderParam("Authorization") String auth) {

        final StringTokenizer tokenizer = new StringTokenizer(body, ":");

        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        Users user = userController.getUserByEmail(email);

        if (userController.login(email, password)) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid email/Password.").build();
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
            String url = uriInfo.getAbsolutePath() + "/" + user.getId(); // url of the posted user
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

}

