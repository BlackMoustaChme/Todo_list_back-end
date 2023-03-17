package rest.builder;

import rest.model.api.in.ITodo;
import rest.model.api.in.IUser;
import rest.model.api.out.ITodoRepository;
import rest.model.api.out.IUserRepository;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;

public class Builder {
    @Inject @Default
    private IUser userModel;

    @Inject @Default
    private IUserRepository userRepository;

    @Inject @Default
    private ITodo todoModel;

    @Inject @Default
    private ITodoRepository todoRepository;

    @Produces @Built
    public IUser buildUserModel() {
        userModel.injectRepository(userRepository);
        return userModel;
    }

    @Produces @Built
    public ITodo buildCarModel() {
        todoModel.injectRepository(todoRepository);
        return todoModel;
    }
}
