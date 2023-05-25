package rest.application.todo.api.out;

//import rest.application.tod/o.implementation.TodoModel;
//import rest.application.user.implementation.UserModel;

public interface Notifiable {

//    void injectTodoModel(TodoModel todoModel);

//    void injectUserModel(UserModel userModel);
    void notifyUser(String clientId, String message);

    void notifyAllUsers(String message);

}
