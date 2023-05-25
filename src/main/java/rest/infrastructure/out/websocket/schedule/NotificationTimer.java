package rest.infrastructure.out.websocket.schedule;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Schedule;
import jakarta.annotation.Resource;
import jakarta.ejb.TimerService;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.annotation.PostConstruct;;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import rest.application.todo.api.in.ITodo;
import rest.application.todo.api.out.ITimer;
import rest.infrastructure.builder.Built;

import java.util.Date;

@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
//@DependsOn("jakarta.transaction.UserTransaction")
@Startup
public class NotificationTimer implements ITimer{

    private String message;

    private String clientId;

    @Resource
    TimerService tservice;

    @Inject
    @Built
    ITodo todoModel;

    @Schedule(second = "*", minute = "*", hour = "*/12")
    public void notifyUser() {
        if (this.message != null && this.clientId != null) {
            todoModel.notifyUser(this.clientId, this.message);
        }
        else {
            String Amessage = "Checked tasks are being deleted every 24 hours";
            todoModel.notifyAllUsers(Amessage);

        }
    }

//    @PostConstruct
//    public void start() {
//        tservice.createIntervalTimer(new Date(), 20000, new TimerConfig());
//    }
//
//    @Timeout
//    public void timeout() {
//        if (this.message != null && this.clientId != null) {
//            todoModel.notifyUser(this.clientId, this.message);
//        }
//        else {
//            String Amessage = "Checked tasks are being deleted every 24 hours";
//            todoModel.notifyAllUsers(Amessage);
//
//        }
//    }

    @Override
    public void setInfo(String clientId, String message) {
        this.clientId = clientId;
        this.message = message;
    }

}
