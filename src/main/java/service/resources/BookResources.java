package service.resources;

import service.ControllerPersistance.DataBookController;
import service.ControllerPersistance.DataLanguageController;
import service.ControllerPersistance.DataLikeController;
import service.model.*;
import service.repository.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/books")
public class BookResources {

    @Context
    private UriInfo uriInfo;

    //return book with specific id
    @GET //GET at http://localhost:9090/booky/books/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookPath(@PathParam("id") int id) {

        DataBookController bookController = new DataBookController();
        Book book = bookController.ShowBookById(id);

        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid book ID!.").build();
        } else {
            return Response.ok(book).build();
        }
    }

    //get all books
    @GET //GET at http://localhost:9090/booky/books?language= OR ?type=
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getBooks(@QueryParam("language") String languageCode, @QueryParam("type") BookType type) {

        List<Book> books;

        DataBookController bookController = new DataBookController();
        DataLanguageController languageController = new DataLanguageController();

        if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("language")){
            Language l = languageController.showLanguageByCode(languageCode);
            books = bookController.BookFilteredWithTypeAndLanguage(type, l);
            System.out.println("hi"+ books);
        }
        else if(uriInfo.getQueryParameters().containsKey("language")){
            Language l = languageController.showLanguageByCode(languageCode);
            books = bookController.BookFilteredWithLanguage(l);
        }
        else if (uriInfo.getQueryParameters().containsKey("type")){
            books = bookController.BookFilteredWithType(type);
        }
        else{
            books = bookController.showAllBooks();
        }

        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {  };
        return Response.ok(entity).build();
    }

    //delete book with specific id
    @DELETE //DELETE at http://localhost:9090/booky/books/3
    @Path("{id}")
    @RolesAllowed("ADMIN")
    public Response deleteBook(@PathParam("id") int bID) {

        DataBookController bookController = new DataBookController();
        bookController.DeleteBook(bID);

        return Response.noContent().build();

    }

    //add book with book object
    @POST //POST at http://localhost:9090/booky/books/
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @RolesAllowed("ADMIN")
    public Response createBook(Book b) {

        DataBookController bookController = new DataBookController();

        if (!bookController.addBook(b)){
            String entity =  "Book with this id is " + b.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        }
        else {
            String url = uriInfo.getAbsolutePath() + "/" + b.getId(); // url of the posted book
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    //update book using the book object
    @PUT //PUT at http://localhost:9090/booky/books/{id}
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @PermitAll
    @RolesAllowed("ADMIN")
    public Response updateBook(@PathParam("id") int id,  Book b) {

        DataBookController bookController = new DataBookController();
        if (bookController.updateBook(id, b)){
            System.out.println(id);
            return Response.noContent().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid book ID.").build();

        }
    }

    //add like with like object
    @POST //POST at http://localhost:9090/booky/books/like
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("like")
    @PermitAll
    public Response AddLikeToBook(Like like) {

        DataLikeController likeController = new DataLikeController();

        if (!likeController.likeBook(like)){
            String entity =  "Like with this id: "  + like.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        }
        else {
            String url = uriInfo.getAbsolutePath() + "/"; // url of the posted like
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @GET //GET at http://localhost:9090/booky/books/MyList
    @Path("MyList")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getLikedBooksByUser(@QueryParam("id") int userId) {

        DataLikeController controller = new DataLikeController();

        List<Book> books;

        books = controller.LikedBooksByUser(userId);

        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {};
        return Response.ok(entity).build();
    }


    @DELETE //GET at http://localhost:9090/booky/books/MyList
    @Path("MyList/{id}/user/{uId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response removeBook(@PathParam("id") int bId, @PathParam("uId") int uId) {

        System.out.println("resources");
        DataLikeController likeController = new DataLikeController();
        System.out.println("ids: " + bId + uId);
        likeController.DeleteBook(bId, uId);

        return Response.noContent().build();
    }


}
