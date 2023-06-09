package rest.application.api.out;


public interface ITimer {

//    void injectTodoModel (TodoModel todoModel);
    void setId(String clientId);

    void updateMessage(String clientId, String message);
}
