package rest.application.user.implementation;

import rest.application.dto.User;
import rest.application.user.api.in.IUser;
import rest.application.user.api.out.IUserRepository;
import rest.application.user.api.out.Interconnectable;

import java.util.logging.Level;
import java.util.logging.Logger;


public class UserModel implements IUser {

    private IUserRepository userRepository;

    private Interconnectable interconnector;

    @Override
    public void injectRepository(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public void injectInterconnector(Interconnectable interconnector) { this.interconnector = interconnector; }


    @Override
    public boolean authUser(String login, String password) {
        try {
            return userRepository.authUser(login, password);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            return false;
        }
    }

    @Override
    public boolean registerUser(String login, String password, String email) {
        try {
            return userRepository.registerUser(login, password, email);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            return false;
        }
    }
    @Override
    public User getUser(String login){
        try {
            return userRepository.getUser(login);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(Integer id) {
        try {
            return userRepository.getUser(id);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean AuthInfo(String login, String password) {
        try {
            return interconnector.userAuth(login, password);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            return false;
        }
    }
}
