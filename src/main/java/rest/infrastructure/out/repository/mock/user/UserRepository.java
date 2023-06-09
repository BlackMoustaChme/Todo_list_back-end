package rest.infrastructure.out.repository.mock.user;

import rest.application.api.out.user.IUserRepository;
import rest.application.dto.User;
import rest.infrastructure.builder.Mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mock
public class UserRepository implements IUserRepository {


    private final static HashMap<Integer, User> mapIdUser = new HashMap<>();
    @Override
    public boolean registerUser(String login, String password, String email) throws Exception {
        int iterator = 0;
        for (Map.Entry<Integer, User> entry : mapIdUser.entrySet()) {
            iterator = entry.getKey();
        }
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setId(iterator + 1);
        boolean flag;
        try {
            if (mapIdUser.containsValue(newUser)) {
                flag = false;
            }
            else {
                mapIdUser.put(iterator + 1, newUser);
                flag = true;
            }
        }
        catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
            return false;
        }
        return flag;
    }

    @Override
    public boolean authUser(String login, String password) throws Exception {
        User user;
//        user.setPassword();
        boolean flag = false;
        try {
//            for (Integer i = 0; i <= mapIdUser.size(); i++) {
//                user = mapIdUser.get(i);
//                if (Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password)) {
//                    flag = true;
//                }
//            }
            for (Map.Entry<Integer, User> entry : mapIdUser.entrySet()) {
                if (Objects.equals(entry.getValue().getLogin(), login) && Objects.equals(entry.getValue().
                        getPassword(), password)) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
            return false;
        }
        return flag;
    }

    @Override
    public User getUser(String login) throws Exception {
        User user = new User();
        try {
//            for (Integer i = 0; i <= mapIdUser.size(); i++) {
//                user = mapIdUser.get(i);
//                if (Objects.equals(user.getLogin(), login)) {
//                    return user;
//                }
//            }
            for (Map.Entry<Integer, User> entry : mapIdUser.entrySet()) {
                if (Objects.equals(entry.getValue().getLogin(), login)) {
                    user = entry.getValue();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
        }
        return user;
    }

    @Override
    public User getUser(Integer id) throws Exception {
        User user = new User();
        try {
            user = mapIdUser.get(id);
        } catch (Exception e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.INFO, null, e);
        }
        return user;
    }
}
