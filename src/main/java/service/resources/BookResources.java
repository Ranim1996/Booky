package service.resources;

import service.DataBookController;
import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.repository.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/books")
public class BookResources {

    @Context
    private UriInfo uriInfo;
    // this has to be static because the service is stateless:
    private static final BookFakeDataStore fakeDataStore = new BookFakeDataStore();

    //return book with specific id
    @GET //GET at http://localhost:9090/booky/books/3
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookPath(@PathParam("id") int id) {
//        Book b = fakeDataStore.getBook(id);
//        if (b == null) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid book ID!.").build();
//        } else {
//            return Response.ok(b).build();
//        }

        DataBookController bookController = new DataBookController();
        Book book = bookController.ShowBookById(id);

        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid book ID!.").build();
        } else {
            return Response.ok(book).build();
        }
    }

    // return all books if id is invalid or return books with that code
//    @GET //GET at http://localhost:9090/booky/books?
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllBooksByLanguage(@QueryParam("language") String languageCode) {
//        List<Book> books;
//        //If query parameter is missing return all books. Otherwise filter books by given languageCode
//        if (uriInfo.getQueryParameters().containsKey("language")){
//            Language l = fakeDataStore.getLanguage(languageCode);
//            if (l == null){ // if language code invalid, return BAD_REQUEST
//                return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid language code.").build();
//            } else {
//                books = fakeDataStore.getBooksByLanguage(l);
//            }
//        } else {
//            books = fakeDataStore.getBooks();
//        }
//        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {  };
//        return Response.ok(entity).build();
//    }

    // return all books if id is invalid or return books with that code
//    @GET //GET at http://localhost:9090/booky/books?type=
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllBooksByType(@QueryParam("type") BookType type) {
//        List<Book> books;
//        //If query parameter is missing return all books. Otherwise filter books by given book type
//        if (uriInfo.getQueryParameters().containsKey("type")){
//            Book b = fakeDataStore.getBookType(type);
//            if (b == null){ // if book type invalid, return BAD_REQUEST
//                return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid book type.").build();
//            } else {
//                books = fakeDataStore.getBooksByBookType(type);
//            }
//        } else {
//            books = fakeDataStore.getBooks();
//        }
//        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {  };
//        return Response.ok(entity).build();
//    }

    //filter books by book type and language
    @GET //GET at http://localhost:9090/booky/books?type= or ?language=
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilteredBooks(@QueryParam("type") BookType type,
                                      @QueryParam("language") String languageCode) {
        List<Book> books;
        //If query parameter is missing return all books. Otherwise filter books by given book type
        if (uriInfo.getQueryParameters().containsKey("type")){
            Book b = fakeDataStore.getBookType(type);
                books = fakeDataStore.getBooksByBookType(type);
            }
        //If query parameter is missing return all books. Otherwise filter books by given language code
        else if(uriInfo.getQueryParameters().containsKey("language")){
            Language l = fakeDataStore.getLanguage(languageCode);
            books= fakeDataStore.getBooksByLanguage(l);
        }
        else {
            books = fakeDataStore.getBooks();
        }
        GenericEntity<List<Book>> entity = new GenericEntity<>(books) {  };
        return Response.ok(entity).build();
    }

    //delete book with specific id
    @DELETE //DELETE at http://localhost:9090/booky/books/3
    @Path("{id}")
    public Response deleteBook(@PathParam("id") int bID) {
//        fakeDataStore.deleteBook(bID);
//        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
//        return Response.noContent().build();

        DataBookController bookController = new DataBookController();
        bookController.DeleteBook(bID);

        return Response.noContent().build();

    }

    //add book with book object
    @POST //POST at http://localhost:9090/booky/books/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book b) {
        if (!fakeDataStore.add(b)){
            String entity =  "Book with this id is " + b.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + b.getId(); // url of the posted book
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    //update book using the book object
    @PUT //PUT at http://localhost:9090/booky/books/{id}
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateBook(@PathParam("id") int id,  Book b) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.update(id, b)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid book ID.").build();
        }
    }


//    @PUT //PUT at http://localhost:9090/booky/books/{id}
//    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
//    @Path("{id}")
//    public Response updateBook(@PathParam("id") int bID,  @FormParam("name") String bookName) {
//        Book b = fakeDataStore.getBook(bID);
//        if (b == null){
//            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid book ID.").build();
//        }
//
//        b.setBookName(bookName);
//        return Response.noContent().build();
//    }

}
