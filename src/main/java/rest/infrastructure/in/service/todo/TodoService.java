package rest.infrastructure.in.service.todo;

import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.DELETE;

import jakarta.ws.rs.container.ContainerRequestContext;
import rest.application.dto.Share;
import rest.infrastructure.builder.Built;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
//import rest.infrastructure.builder.Built;
import rest.infrastructure.in.interceptor.TokenRequired;
import rest.application.api.in.IUser;
import rest.application.api.in.ITodo;
import rest.application.dto.Todo;
@Path("/todo")
public class TodoService {

    /*
    @Inject
    @Built
    ITodo todoModel;

    @Inject
    @Built
    IUser userModel;

     */

    ////////////////
    @Inject
    @Built
    ITodo todoMockModel;


    private Jsonb jsonb = JsonbBuilder.create();

    @GET
    @TokenRequired
    @Path("/")
    public Response getUserTodos(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        ArrayList<Todo> todos = todoMockModel.getUserTodos(Integer.parseInt(id));
        String resultJson = jsonb.toJson(todos);
        return Response.ok(resultJson).build();
    }


    @GET
    @TokenRequired
    @Path("/notifications")
    public Response getNumberOfCheckedTodos(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        String clientId = requestContext.getHeaderString("ClientId");
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        todoMockModel.getNumberOfCheckedTodos(Integer.parseInt(id), clientId);
        return Response.ok("OK").build();
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
            todoMockModel.addTodo(todo, Integer.parseInt(id));
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
//        return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok().build();
    }


    @PUT
    @TokenRequired
    @Path("/")
    public Response updateTodo(@Context ContainerRequestContext requestContext, String jsonTodo) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Todo todo;
//        String jsonUpdateId = requestContext.getHeaderString("Data");
        String updateId = requestContext.getUriInfo().getQueryParameters().getFirst("id");
        try {
            try {
                todo = jsonb.fromJson(jsonTodo, Todo.class);
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
            todoMockModel.updateTodo(todo, Integer.parseInt(updateId));
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
//        return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(updateId).build();
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
//        String t = requestContext.getUriInfo().;
        List<Todo> todosId = new ArrayList<>();
        try {
            try {
               todosId = jsonb.fromJson(jsonDeleteId, new ArrayList<Todo>(){}.getClass().getGenericSuperclass());
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
            todoMockModel.deleteTodo(todosId);
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(id).build();
    }

//**************************SHARES******************************
/*
    @GET
    @TokenRequired
    @Path("/shared/reading")
    public Response getSharedTodosReadable(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        ArrayList<Todo> todos = todoModel.getSharedTodosReadable(Integer.parseInt(id));
        String resultJson = jsonb.toJson(todos);
        return Response.ok(resultJson).build();
    }

    @GET
    @TokenRequired
    @Path("/shared/redaction")
    public Response getSharedTodosRedactable(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        ArrayList<Todo> todos = todoModel.getSharedTodosRedactable(Integer.parseInt(id));
        String resultJson = jsonb.toJson(todos);
        return Response.ok(resultJson).build();
    }


    @POST
    @TokenRequired
    @Path("/shared")
    public Response shareTodo(@Context ContainerRequestContext requestContext, String jsonShare) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Share share;
        try {
            try {
                share = jsonb.fromJson(jsonShare, Share.class);
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
            todoModel.shareTodo(share);
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
//        return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok().build();
    }

    @PUT
    @TokenRequired
    @Path("/shared")
    public Response updateShareInfo(@Context ContainerRequestContext requestContext, String jsonShare) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Share share;
//        String jsonUpdateId = requestContext.getHeaderString("Data");
        String updateId = requestContext.getUriInfo().getQueryParameters().getFirst("id");
        try {
            try {
                share = jsonb.fromJson(jsonShare, Share.class);
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
            todoModel.updateShareInfo(share);
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
//        return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(updateId).build();
    }

    @DELETE
    @TokenRequired
    @Path("/ban")
    public Response banUser(@Context ContainerRequestContext requestContext) {
        String id = requestContext.getProperty("id").toString();
        if(id.equals("Token not valid")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String jsonDeleteId = requestContext.getHeaderString("Data");
//        String t = requestContext.getUriInfo().;
        List<Share> sharesId = new ArrayList<>();
        try {
            try {
                sharesId = jsonb.fromJson(jsonDeleteId, new ArrayList<Share>(){}.getClass().getGenericSuperclass());
            } catch (Exception e) {
                throw new Exception("Error JSON transforming");
            }
//            todoModel.deleteUser(sharesId);
        } catch (JsonbException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(id).build();
    }
*/
}
