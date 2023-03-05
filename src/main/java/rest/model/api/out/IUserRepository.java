package rest.model.api.out;

import rest.model.api.dto.User;
import rest.model.api.out.IUserRepository;
public interface IUserRepository {

    public boolean registerUser(String login, String password, String email) throws Exception;

    public boolean authUser(String login, String password) throws Exception;

    public User getUser(String login) throws Exception;

}

