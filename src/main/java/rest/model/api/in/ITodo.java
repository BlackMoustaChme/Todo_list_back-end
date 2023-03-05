package rest.model.api.in;

import rest.model.api.dto.Todo;
import rest.model.api.out.ITodoRepository;

import java.util.ArrayList;

public interface ITodo {

    void injectRepository(ITodoRepository todoRepository);

    public ArrayList<Todo> getUserTodos(Integer id);

    public Todo addTodo(Todo todo);

    public void deleteTodo(ArrayList<Integer> id);
}
