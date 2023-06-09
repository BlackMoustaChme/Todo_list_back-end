package rest.application.api.out;


public interface Notifiable {

    void notifyUser(String clientId, String message);

    void notifyAllUsers(String message);

}
