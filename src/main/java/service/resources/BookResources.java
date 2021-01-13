package service.resources;

import service.ControllerPersistance.DataBookController;
import service.ControllerPersistance.DataLanguageController;
import service.ControllerPersistance.DataLikeController;
import service.ControllerPersistance.DataStatisticsController;
import service.model.*;
import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;
import service.repository.BookyDatabaseException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@Path("/books")
public class BookResources {

    DataBookController bookController = new DataBookController();
    DataLikeController likeController = new DataLikeController();
    DataStatisticsController statisticsController = new DataStatisticsController();

    @Context
    private UriInfo uriInfo;

    //return book with specific id
    @GET //GET at http://localhost:9090/booky/books/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookPath(@PathParam("id") int id) throws BookyDatabaseException, SQLException, URISyntaxException {

        Book book = bookController.showBookById(id);

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
    public Response getBooks(@QueryParam("language") String languageCode, @QueryParam("type") BookType type
            , @QueryParam("bookName") String name) throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Book> books;

        DataLanguageController languageController = new DataLanguageController();

        if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("language")){
            Language l = languageController.showLanguageByCode(languageCode);
            books = bookController.bookFilteredWithTypeAndLanguage(type, l);
        }
        else if(uriInfo.getQueryParameters().containsKey("language")){
            Language l = languageController.showLanguageByCode(languageCode);
            books = bookController.bookFilteredWithLanguage(l);
        }
        else if (uriInfo.getQueryParameters().containsKey("type")){
            books = bookController.bookFilteredWithType(type);
        }
        else if (uriInfo.getQueryParameters().containsKey("bookName")){
            books = bookController.bookFilterByFirstNameChars(name);
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
    @RolesAllowed("Admin")
    public Response deleteBook(@PathParam("id") int bID) throws BookyDatabaseException, SQLException, URISyntaxException {

        bookController.deleteBook(bID);
        return Response.noContent().build();

    }

    //add book with book object
    @POST //POST at http://localhost:9090/booky/books/
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response createBook(Book b) throws BookyDatabaseException, SQLException, URISyntaxException {

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
    @RolesAllowed("Admin")
    public Response updateBook(@PathParam("id") int id,  Book b) throws BookyDatabaseException, SQLException, URISyntaxException {

        if (bookController.updateBook(id, b)){
            return Response.noContent().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid book ID.").build();

        }
    }

    //add like with like object
    @POST //POST at http://localhost:9090/booky/books/like
    @Path("like")
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response addLikeToBook(Like like) throws BookyDatabaseException, SQLException, URISyntaxException {

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
    @RolesAllowed("Reader")
    public Response getLikedBooksByUser(@QueryParam("id") int userId) throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Book> books;

        books = likeController.likedBooksByUser(userId);

        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {};
        return Response.ok(entity).build();
    }


    @DELETE //GET at http://localhost:9090/booky/books/MyList
    @Path("MyList/{id}/user/{uId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Reader")
    public Response removeBook(@PathParam("id") int bId, @PathParam("uId") int uId) throws BookyDatabaseException, SQLException, URISyntaxException {
        
        likeController.deleteBook(bId, uId);

        return Response.noContent().build();
    }

    /*******************************Statistics**************************************/
    @GET //GET at http://localhost:9090/booky/books/Majority/Type
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Majority/Type")
    @RolesAllowed("Admin")
    public Response countStatisticsPerType() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<StatisticsType> statistics = statisticsController.staisticsPerType();

        return Response.ok(statistics).build();
    }

    @GET //GET at http://localhost:9090/booky/books/Majority/Language
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Majority/Language")
    @RolesAllowed("Admin")
    public Response countStatisticsPerLanguage() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<StatisticsLanguage> statistics = statisticsController.statisticsPerLanguage();

        return Response.ok(statistics).build();
    }


}
