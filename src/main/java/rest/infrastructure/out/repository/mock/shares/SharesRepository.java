package rest.infrastructure.out.repository.mock.shares;

import rest.application.api.out.todo.IShareRepository;
import rest.application.dto.Share;
import rest.application.dto.Todo;
import rest.infrastructure.builder.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mock
public class SharesRepository implements IShareRepository {

    private final static HashMap<Integer, Share> mapIdShare = new HashMap<>();
    @Override
    public ArrayList<Todo> getSharedTodosReadable(Integer id) throws Exception {
        try {

        } catch (Exception e) {
            Logger.getLogger(SharesRepository.class.getName()).log(Level.INFO, null, e);
        }
        return null;
    }

    @Override
    public ArrayList<Todo> getSharedTodosRedactable(Integer id) throws Exception {
        try {

        } catch (Exception e) {
            Logger.getLogger(SharesRepository.class.getName()).log(Level.INFO, null, e);
        }
        return null;
    }

    @Override
    public void shareTodo(Share share) throws Exception {
        try {

        } catch (Exception e) {
            Logger.getLogger(SharesRepository.class.getName()).log(Level.INFO, null, e);
        }

    }

    @Override
    public void deleteUser(Integer id) throws Exception {
        try {

        } catch (Exception e) {
            Logger.getLogger(SharesRepository.class.getName()).log(Level.INFO, null, e);
        }
    }

    @Override
    public void updateShareInfo(Share share) throws Exception {
        try {

        } catch (Exception e) {
            Logger.getLogger(SharesRepository.class.getName()).log(Level.INFO, null, e);
        }
    }
}
