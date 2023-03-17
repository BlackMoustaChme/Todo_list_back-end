package rest.service.todo;

import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.container.ContainerRequestContext;
import rest.builder.Built;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import rest.interceptor.TokenRequired;
import rest.model.api.in.IUser;
import rest.model.api.in.ITodo;
import rest.model.api.dto.Todo;
@Path("/todo")
public class TodoService {

    @Inject
    @Built
    ITodo todoModel;

    @Inject
    @Built
    IUser userModel;

    private Jsonb jsonb = JsonbBuilder.create();

    @GET
    @TokenRequired
    @Path("/")
    public Response getUserTodos(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        ArrayList<Todo> todos = todoModel.getUserTodos(Integer.parseInt(id));
        String resultJson = jsonb.toJson(todos);
        return Response.ok(resultJson).build();
    }

    @POST
    @TokenRequired
    @Path("/")
    public Response addTodo(@Context ContainerRequestContext requestContext, String jsonTodo) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Todo todo;
        try {
            try {
               todo = jsonb.fromJson(jsonTodo, Todo.class);
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
            todoModel.addTodo(todo, Integer.parseInt(id));
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
//        return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok().build();
    }




    @DELETE
    @TokenRequired
    @Path("/deletion")
    public Response deleteTodo(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String jsonDeleteId = requestContext.getHeaderString("Data");
        List<Todo> todosId = new ArrayList<>();
        try {
            try {
               todosId = jsonb.fromJson(jsonDeleteId, new ArrayList<Todo>(){}.getClass().getGenericSuperclass());
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
            todoModel.deleteTodo(todosId);
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(id).build();
    }

}
