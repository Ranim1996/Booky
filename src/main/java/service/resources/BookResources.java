package service.resources;

import service.ControllerPersistance.DataBookController;
import service.ControllerPersistance.DataLanguageController;
import service.ControllerPersistance.DataLikeController;
import service.ControllerPersistance.DataStatisticsController;
import service.model.*;
import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
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
    public Response getBookPath(@PathParam("id") int id) {

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
            , @QueryParam("bookName") String name) {

        List<Book> books;

        DataLanguageController languageController = new DataLanguageController();

        if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("language")){
            Language l = languageController.showLanguageByCode(languageCode);
            books = bookController.bookFilteredWithTypeAndLanguage(type, l);
            System.out.println("hi"+ books);
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
    @PermitAll
    public Response deleteBook(@PathParam("id") int bID) {

        System.out.println("in book resources");
        bookController.deleteBook(bID);

        return Response.noContent().build();

    }

    //add book with book object
    @POST //POST at http://localhost:9090/booky/books/
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @RolesAllowed("Admin")
    public Response createBook(Book b) {

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
    @RolesAllowed("Admin")
    public Response updateBook(@PathParam("id") int id,  Book b) {

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
    public Response addLikeToBook(Like like) {

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

        List<Book> books;

        books = likeController.likedBooksByUser(userId);

        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {};
        return Response.ok(entity).build();
    }


    @DELETE //GET at http://localhost:9090/booky/books/MyList
    @Path("MyList/{id}/user/{uId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response removeBook(@PathParam("id") int bId, @PathParam("uId") int uId) {
        
        likeController.deleteBook(bId, uId);

        return Response.noContent().build();
    }

    /*******************************Statistics**************************************/
    @GET //GET at http://localhost:9090/booky/books/Majority/Type
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Majority/Type")
    @PermitAll
    public Response countStatisticsPerType() {

        List<StatisticsType> statistics = statisticsController.staisticsPerType();
        System.out.println("count resources statistics: "+ statistics);

        return Response.ok(statistics).build();
    }

    @GET //GET at http://localhost:9090/booky/books/Majority/Language
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Majority/Language")
    @PermitAll
    public Response countStatisticsPerLanguage() {

        List<StatisticsLanguage> statistics = statisticsController.statisticsPerLanguage();
        System.out.println("count resources statistics: "+ statistics);

        return Response.ok(statistics).build();
    }


}
