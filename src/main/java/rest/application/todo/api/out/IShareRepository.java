package rest.application.todo.api.out;

import rest.application.dto.Share;
import rest.application.dto.Todo;

import java.util.ArrayList;

public interface IShareRepository {

    public ArrayList<Todo> getSharedTodos(Integer id) throws Exception;

    public void shareTodo(Share share) throws Exception;

    public void deleteUser(Integer id) throws Exception;

    public void updateShareInfo(Share share) throws Exception;
}
