package service.resources;

import service.ControllerPersistance.DataUserController;
import service.model.Users;
import service.model.Language;
import service.repository.*;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.StringTokenizer;

@Path("/users")
public class UserResources {

    @Context
    private UriInfo uriInfo;
    // this has to be static because the service is stateless:
    private static final UsersFakeDataStore fakeDataStore = new UsersFakeDataStore();

    //return user with specific id
    @GET //GET at http://localhost:9090/booky/users/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPath(@PathParam("id") int id, @HeaderParam("Authorization") String auth) {

        DataUserController userController = new DataUserController();

        if (!userController.isIdAndAuthSame(id, auth)){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email and/or password.").build();
        }
        else{
            Users u = userController.ShowUserById(id);
            if(u != null){
                return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid id.").build();
            }
            else {
                return Response.ok(u).build();
            }
        }
    }

    // return all users if id is invalid or return books with that code
    @GET //GET at http://localhost:9090/booky/users?
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@QueryParam("language") String languageCode) {
        List<Users> users;
        //If query parameter is missing return all books. Otherwise filter books by given languageCode
        if (uriInfo.getQueryParameters().containsKey("language")){
            Language l = fakeDataStore.getLanguage(languageCode);
            if (l == null){ // if language code invalid, return BAD_REQUEST
                return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid language code.").build();
            } else {
                users = fakeDataStore.getUsers(l);
            }
        } else {
            users = fakeDataStore.getUsers();
        }
        GenericEntity<List<Users>> entity = new GenericEntity<>(users) {  };
        return Response.ok(entity).build();
    }

    //delete user with specific id
    @DELETE //DELETE at http://localhost:9090/booky/users/3
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int uID) {
        fakeDataStore.deleteUser(uID);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }

    //add book with user object
    @POST //POST at http://localhost:9090/booky/users/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Users u) {
        if (!fakeDataStore.addUser(u)){
            String entity =  "User with this id is " + u.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + u.getId(); // url of the created user
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    //update user personal infirmation using the user object
    @PUT //PUT at http://localhost:9090/booky/users/{id}
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, Users u) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        DataUserController userController = new DataUserController();
        if (userController.updateUser(id, u)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid user ID.").build();
        }
    }

    //log into the web application using email and password
    @POST
    @Path("login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response LoginUser(String body) {

        DataUserController userController = new DataUserController();

        final StringTokenizer tokenizer = new StringTokenizer(body, ":");

        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        Users user = userController.getUserByEmail(email);

        if (userController.login(email, password)) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid email.").build();
        }
    }

}

