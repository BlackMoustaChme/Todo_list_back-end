package rest.infrastructure.builder;

import rest.application.todo.api.in.ITodo;
import rest.application.todo.api.out.IShareRepository;
import rest.application.todo.api.out.ITimer;
import rest.application.user.api.out.Interconnectable;
import rest.application.todo.api.out.Notifiable;
import rest.application.user.api.in.IUser;
import rest.application.todo.api.out.ITodoRepository;
import rest.application.user.api.out.IUserRepository;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;

public class Builder {
    @Inject @Default
    private IUser userModel;

    @Inject @Default
    private IUserRepository userRepository;

    @Inject @Default
    private Interconnectable interconnector;

    @Inject @Default
    private ITodo todoModel;

    @Inject @Default
    private ITodoRepository todoRepository;

    @Inject @Default
    private IShareRepository shareRepository;

    @Inject @Default
    private Notifiable notifier;

    @Inject @Default
    private ITimer timer;


    @Produces @Built
    public IUser buildUserModel() {
        userModel.injectRepository(userRepository);
        userModel.injectInterconnector(interconnector);
        return userModel;
    }

    @Produces @Built
    public ITodo buildTodoModel() {
        todoModel.injectRepository(todoRepository);
        todoModel.injectShareRepository(shareRepository);
        todoModel.injectNotifier(notifier);
        todoModel.injectTimer(timer);
        return todoModel;
    }
}
