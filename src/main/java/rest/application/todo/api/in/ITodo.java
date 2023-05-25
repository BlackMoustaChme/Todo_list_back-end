package rest.application.todo.api.in;

import rest.application.dto.Share;
import rest.application.dto.Todo;
import rest.application.todo.api.out.*;
import rest.application.user.api.out.Interconnectable;

import java.util.ArrayList;
import java.util.List;

public interface ITodo {

    void injectRepository(ITodoRepository todoRepository);

    void injectShareRepository(IShareRepository shareRepository);

    void injectNotifier(Notifiable notifier);

    void injectTimer(ITimer timer);

    void injectExecutor(Executable executor);


    public ArrayList<Todo> getUserTodos(Integer id);

    public void addTodo(Todo todo, Integer userId);

    public void deleteTodo(List<Todo> todosList);

    public void updateTodo(Todo todo, Integer id);

    public void shareTodo(Share share);

    public void deleteUser(Integer id);

    public void updateShareInfo(Share share);

    public ArrayList<Todo> getSharedTodos(Integer id);

    public void getNumberOfCheckedTodos(Integer id, String clientId);

    public void notifyUser(String clientId, String message);

    public void notifyAllUsers(String message);

}
