package rest.application.api.out.user;

import rest.application.dto.User;

public interface IUserRepository {

    public boolean registerUser(String login, String password, String email) throws Exception;

    public boolean authUser(String login, String password) throws Exception;

    public User getUser(String login) throws Exception;

    public User getUser(Integer id) throws Exception;

}

