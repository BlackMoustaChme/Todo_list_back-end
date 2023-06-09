package rest.infrastructure.out.websocket.notifications;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import rest.application.api.out.Notifiable;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/notifications")
public class NotificationsService implements Notifiable{

    private final static ConcurrentLinkedQueue<Session> queue = new ConcurrentLinkedQueue<>();
    private final static ConcurrentHashMap<String,Session> mapIdSs = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<Session,String> mapSsId = new ConcurrentHashMap<>();

//    @Inject
//    @Built
//    IUser userModel;

//    @Inject
//    @Built
//    ITodo todoModel;

    @Override
    public void notifyAllUsers(String message) {
        try {
            for (Session sess : queue) {
                if (sess.isOpen()) {
                    sess.getBasicRemote().sendText(message);
                }
            }
        }
        catch (IOException ioe) {
            Logger.getLogger(NotificationsService.class.getName()).log(Level.INFO, null, ioe);
        }
    }

    @Override
    public void notifyUser(String id, String message) {
        try {
            Session session = mapIdSs.get(id);
            if (id != null && session != null) {
                session.getBasicRemote().sendText(message);
            }
        }
        catch (IOException ioe) {
            Logger.getLogger(NotificationsService.class.getName()).log(Level.INFO, null, ioe);
        }
    }

    @OnOpen
    public void connectionOpened(Session session) {
        queue.add(session);
    }

    @OnClose
    public void connectionClosed(Session session) {
        queue.remove(session);
        String message = mapSsId.remove(session);
        mapIdSs.remove(message);
    }

    @OnMessage
    public void processMessage(Session session, String message) {
//        User user = userModel.getUser(login);
//        todoModel.getNumberOfCheckedTodos(user.getId(), message);
        mapIdSs.put(message, session);
        mapSsId.put(session, message);
    }

}
