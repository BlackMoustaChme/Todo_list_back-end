package rest.application.api.out.todo;

import rest.application.dto.Todo;

import java.util.ArrayList;

public interface ITodoRepository {

    public ArrayList<Todo> getUserTodos(Integer id) throws Exception;

    public Todo getTodo(Integer id) throws Exception;

    public void addTodo(Todo todo, Integer userId) throws Exception;

    public void deleteTodo(Integer id) throws Exception;

    public void updateTodo(Todo todo, Integer id) throws Exception;

    public long getNumberOfCheckedTodos(Integer id) throws Exception;
}
