package rest.application.user.api.in;

import rest.application.dto.User;
import rest.application.user.api.out.IUserRepository;
import rest.application.user.api.out.Interconnectable;

public interface IUser {
    void injectRepository(IUserRepository userRepository);

    void injectInterconnector(Interconnectable interconnector);
    public boolean authUser(String login, String password);

    public boolean registerUser(String login, String password, String email);

    public User getUser(String login);

    public User getUser(Integer id);

    public boolean AuthInfo(String login, String password);
}

