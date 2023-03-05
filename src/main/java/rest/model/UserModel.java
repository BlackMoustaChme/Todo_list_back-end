package rest.model;

import rest.model.api.dto.User;
import rest.model.api.in.IUser;
import rest.model.api.out.IUserRepository;

import java.util.logging.Level;
import java.util.logging.Logger;


public class UserModel implements IUser {

    private IUserRepository userRepository;
    @Override
    public void injectRepository(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public boolean authUser(String login, String password) {
        try {
            return userRepository.authUser(login, password);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            return false;
        }
//        database realization
//        boolean db = false;
//        try {
//            db = new UserDatabaseHandler().authUser(login, password);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return db;//2
//        return false;
    }

    @Override
    public boolean registerUser(String login, String password, String email) {
        try {
            return userRepository.registerUser(login, password, email);
        } catch (Exception e) {
            Logger.getLogger(UserModel.class.getName()).log(Level.INFO, null, e);
            return false;
        }
//        database realization
//        boolean db = false;
//        try {
//            db = new UserDatabaseHandler().registerUser(login, password, lastName, name, middleName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return db;
//        return true;
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
}
