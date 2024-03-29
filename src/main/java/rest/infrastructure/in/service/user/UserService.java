package rest.infrastructure.in.service.user;

import rest.infrastructure.builder.Built;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.inject.Inject;
import rest.infrastructure.in.interceptor.TokenRequired;
import rest.application.dto.User;
import rest.application.api.in.IUser;
import rest.infrastructure.in.service.token.Token;
//import rest.util.UserDatabaseHandler;

//Надо переписать с httpHeaders на ContainerRequestContext там где это необходимо

@Path("/user")
public class UserService {

    @Context
    ContainerRequestContext requestContext;
    @Inject
    @Built
    IUser userModel;

    private Jsonb jsonb = JsonbBuilder.create();

    @GET
    @Path("/test")
    @Produces("text/plain")
    public String ping() {
        return "OK";
    }

    @GET
    @TokenRequired
    @Path("/")
    public Response getUser(ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        User user = userModel.getUser(Integer.parseInt(id));
        String resultJson = jsonb.toJson(user);
        return Response.ok(resultJson).build();
    }


    @POST
    @Path("/authorization")
    @Produces("application/json")
    public Response authorization(String userJson) {
        User user;
        user = jsonb.fromJson(userJson, User.class);
        String login = user.getLogin();
        String password = user.getPassword();
        if (login == "" || login == null) {//попався
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (userModel.AuthInfo(login, password)) {
            User userToken = userModel.getUser(login);
            String token = Token.generateToken(userToken.getId());
            String resultJSON = jsonb.toJson(token);
            return Response.ok(resultJSON).build();
//         return Response.ok("Yes").build();//На будущее. Лучше переработать под пользование response.status примеры код влада
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
//     return Response.ok(resultJSON).build();
    }
    //
    @POST
    @Path("/registration")
    public Response registration(String userJson) {
//        String authToken = httpHeaders.getHeaderString("Authorization");

        User user = jsonb.fromJson(userJson, User.class);
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        if (userModel.registerUser(login, password, email)){
            User userToken = userModel.getUser(login);
            String token = Token.generateToken(userToken.getId());
            String resultJSON = jsonb.toJson(token);
            return Response.ok(resultJSON).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
