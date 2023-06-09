package rest.application.api.out.todo;

import rest.application.dto.Share;
import rest.application.dto.Todo;

import java.util.ArrayList;

public interface IShareRepository {

    public ArrayList<Todo> getSharedTodosReadable(Integer id) throws Exception;

    public ArrayList<Todo> getSharedTodosRedactable(Integer id) throws Exception;

    public void shareTodo(Share share) throws Exception;

    public void deleteUser(Integer id) throws Exception;

    public void updateShareInfo(Share share) throws Exception;
}
