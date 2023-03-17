package rest.model.api.out;

import rest.model.api.dto.Todo;

import java.util.ArrayList;

public interface ITodoRepository {

    public ArrayList<Todo> getUserTodos(Integer id) throws Exception;

    public void addTodo(Todo todo, Integer userId) throws Exception;

    public void deleteTodo(Integer id) throws Exception;
}
