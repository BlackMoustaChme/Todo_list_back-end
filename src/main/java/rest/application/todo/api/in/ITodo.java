package rest.application.todo.api.in;

import rest.application.dto.Todo;
import rest.application.todo.api.out.Notifiable;
import rest.application.todo.api.out.ITodoRepository;

import java.util.ArrayList;
import java.util.List;

public interface ITodo {

    void injectRepository(ITodoRepository todoRepository);

    void injectAsync(Notifiable notifier);

    public ArrayList<Todo> getUserTodos(Integer id);

    public void addTodo(Todo todo, Integer userId);

    public void deleteTodo(List<Todo> todosList);

    public void updateTodo(Todo todo, Integer id);

//    public void getNumberOfCheckedTodos(Integer id, String clientId);
}
