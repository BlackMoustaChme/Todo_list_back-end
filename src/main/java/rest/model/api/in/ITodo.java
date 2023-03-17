package rest.model.api.in;

import rest.model.api.dto.Todo;
import rest.model.api.out.ITodoRepository;

import java.util.ArrayList;
import java.util.List;

public interface ITodo {

    void injectRepository(ITodoRepository todoRepository);

    public ArrayList<Todo> getUserTodos(Integer id);

    public void addTodo(Todo todo, Integer userId);

    public void deleteTodo(List<Todo> todosList);
}
