package rest.application.todo.api.out;

import rest.application.dto.Todo;

import java.util.ArrayList;

public interface ITodoRepository {

    public ArrayList<Todo> getUserTodos(Integer id) throws Exception;

    public void addTodo(Todo todo, Integer userId) throws Exception;

    public void deleteTodo(Integer id) throws Exception;

    public void updateTodo(Todo todo, Integer id) throws Exception;

    public int getNumberOfCheckedTodos(Integer id);
}
