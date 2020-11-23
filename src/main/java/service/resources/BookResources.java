package service.resources;

import service.ControllerPersistance.DataBookController;
import service.ControllerPersistance.DataLanguageController;
import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.*;

import javax.annotation.security.PermitAll;
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
    public Response deleteBook(@PathParam("id") int bID) {

        DataBookController bookController = new DataBookController();
        bookController.DeleteBook(bID);

        return Response.noContent().build();

    }

    //add book with book object
    @POST //POST at http://localhost:9090/booky/books/
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
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

}
