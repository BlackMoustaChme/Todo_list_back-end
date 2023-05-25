package rest.application.todo.api.out;

//import rest.application.todo.implementation.TodoModel;

public interface ITimer {

//    void injectTodoModel (TodoModel todoModel);
    void setInfo(String clientId, String message);
}
