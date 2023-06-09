package rest.infrastructure.out.websocket.schedule;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;
import jakarta.ejb.Schedule;
import jakarta.ejb.TimerService;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
;
import jakarta.inject.Inject;
import rest.application.api.in.ITodo;
import rest.application.api.out.ITimer;
import rest.infrastructure.builder.Built;

import java.util.concurrent.ConcurrentHashMap;

@Singleton
//@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
//@DependsOn("jakarta.transaction.UserTransaction")
@Startup
public class NotificationTimer implements ITimer{

//    private String message;
//
//    private String clientId;
    private final static ConcurrentHashMap<String, String> clientIdMessageMap = new ConcurrentHashMap<>();


    @Inject
    @Built
    ITodo todoModel;

    @Schedule(second = "*/20", minute = "*", hour = "*")
    public void notifyUser() {
        if (clientIdMessageMap.isEmpty()) {
            String Amessage = "Checked tasks are being deleted every 24 hours";
            todoModel.notifyAllUsers(Amessage);
        }
        else {
            clientIdMessageMap.forEach((clientId, message)-> todoModel.notifyUser(clientId, message));
        }
    }

    @Override
    public void setId(String clientId) {
        clientIdMessageMap.putIfAbsent(clientId, "default message");
    }

    @Override
    public void updateMessage(String clientId, String message) {
        clientIdMessageMap.replace(clientId, message);
    }

}
