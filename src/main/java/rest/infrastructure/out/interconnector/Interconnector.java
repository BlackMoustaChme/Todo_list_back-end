package rest.infrastructure.out.interconnector;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.JMSConsumer;
import jakarta.jms.Queue;
import rest.application.api.out.Interconnectable;
import jakarta.jms.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Interconnector implements Interconnectable {

//    @Inject @Built
//    private IUser userModel;
    @Resource(mappedName = "jms/WebAppFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/FirstTestQueue")
    private Queue firstQueue;

    @Resource(mappedName = "jms/SecondTestQueue")
    private Queue secondQueue;

    @Override
    public boolean userAuth(String login, String password) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(firstQueue);
            Message message = context.createMessage();
            message.setStringProperty("login", login);
            message.setStringProperty("password", password);
            producer.send(secondQueue, message);
            Message newMessage = consumer.receive();
            boolean isAuth = newMessage.getBooleanProperty("status");
            return isAuth;
//            return true;
        } catch (Exception e) {
            Logger.getLogger(Interconnector.class.getName()).log(Level.INFO, null, e);
            return false;
        }
    }
}
