package rest.application.user.api.in;

import rest.application.dto.User;
import rest.application.user.api.out.IUserRepository;

public interface IUser {
    void injectRepository(IUserRepository userRepository);
    public boolean authUser(String login, String password);

    public boolean registerUser(String login, String password, String email);

    public User getUser(String login);

    public User getUser(Integer id);
}

