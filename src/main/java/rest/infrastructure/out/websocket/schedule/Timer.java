package rest.infrastructure.out.websocket.schedule;

import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TimerService;
import jakarta.inject.Inject;
import rest.application.todo.api.in.ITodo;
import rest.application.todo.api.out.Notifiable;
import rest.infrastructure.builder.Built;
import rest.infrastructure.out.websocket.notifications.NotificationsService;

@Singleton
@Startup
public class Timer{

    @Resource
    TimerService tservice;

//    @Inject
//    @Built
//    ITodo todoModel;

//    @Override
    @Schedule(second = "*/20", minute = "*", hour = "*")
    public void notifyUsers() {
        String message = "Checked tasks are being deleted everu 24 hours";
        NotificationsService.sendAll(message);
    }

}
