package service.resources;

import service.ControllerPersistance.DataUserController;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    // requestContext contains information about the HTTP request message


    @Override
    public void filter(ContainerRequestContext requestContext)
    {

        Method method = resourceInfo.getResourceMethod();

        // if access is allowed for all -> do not check anything further : access is approved for all
        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        // if access is denied for all: deny access
        if (method.isAnnotationPresent(DenyAll.class)) {
            Response response = Response.status(Response.Status.FORBIDDEN).build();
            requestContext.abortWith(response);
            return;
        }

        /* here you do
        1. the AUTHENTICATION first (as explained in previous sections), and
        2. if AUTHENTICATION succeeds, you do the authorization like this:
        */
        if (method.isAnnotationPresent(RolesAllowed.class)) {
            // get allowed roles for this method
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> rolesSet = new
                    HashSet<String>(Arrays.asList(rolesAnnotation.value()));
             /* isUserAllowed : implement this method to check if this user has any of
             the roles in the rolesSet
             if not isUserAllowed abort the requestContext with FORBIDDEN response*/
//            if (!isUserAllowed(username, password, rolesSet)) {
//                Response response = Response.status(Response.Status.FORBIDDEN).build();
//                requestContext.abortWith(response);
//                return;
//            }
        }


        final String AUTHORIZATION_PROPERTY = "Authorization";
        final String AUTHENTICATION_SCHEME = "Basic";

        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present: abort with UNAUTHORIZED and stop
        if (authorization == null || authorization.isEmpty()) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Missing username and/or password.").build();
            requestContext.abortWith(response);
            return;
        }
        //Get encoded username and password
        final String encodedCredentials = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password into one string
        String credentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        //Split username and password tokens in credentials
        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        System.out.println(email);
        System.out.println(password);

        //Check if username and password are valid (e.g., database)
        //If not valid: abort with UNAUTHORIED and stop
        if (!isValidUser(email, password)) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email and/or password.").build();
            requestContext.abortWith(response);
            return;
        }


    }

   // check the validation when log in
   private boolean isValidUser(String email, String password) {
        DataUserController userController = new DataUserController();
        boolean valid;
        valid = userController.login(email, password);
        return valid;
    }

    // check the user is allowed
    private boolean isUserAllowed(Set<String> rolesSet) {
        if (rolesSet.contains("READER") || rolesSet.contains("ADMIN")){
            return true;
        }

        return false;
    }

}
