package rest.infrastructure.builder;

import rest.application.api.in.ITodo;
import rest.application.api.out.Executable;
import rest.application.api.out.todo.IShareRepository;
import rest.application.api.out.ITimer;
import rest.application.api.out.Interconnectable;
import rest.application.api.out.Notifiable;
import rest.application.api.in.IUser;
import rest.application.api.out.todo.ITodoRepository;
import rest.application.api.out.user.IUserRepository;

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

    @Inject @Default
    private Executable executor;

    @Inject @Mock
    private IUserRepository userMockRepository;

    @Inject @Mock
    private ITodoRepository todoMockRepository;


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
        todoModel.injectExecutor(executor);
        return todoModel;
    }

    @Produces
    public IUser buildMockUserModel() {
        userModel.injectRepository(userMockRepository);
        userModel.injectInterconnector(interconnector);
        return userModel;
    }

    @Produces
    public ITodo buildMockTodoModel() {
        todoModel.injectRepository(todoMockRepository);
        todoModel.injectShareRepository(shareRepository);
        todoModel.injectNotifier(notifier);
        todoModel.injectTimer(timer);
        todoModel.injectExecutor(executor);
        return todoModel;
    }
}
