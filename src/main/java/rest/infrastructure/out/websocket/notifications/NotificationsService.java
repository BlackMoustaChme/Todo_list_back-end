package rest.infrastructure.out.websocket.notifications;

import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import rest.application.dto.User;
import rest.application.todo.api.in.ITodo;
import rest.application.todo.api.out.Notifiable;
import rest.application.user.api.in.IUser;
import rest.infrastructure.builder.Built;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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
        }
    }

    @Override
    public void notifyUser(String id, String message) {
        try {
            Session session = mapIdSs.get(id);
            if (id!=null) {
                session.getBasicRemote().sendText(message);
            }
        }
        catch (IOException ioe) {
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
