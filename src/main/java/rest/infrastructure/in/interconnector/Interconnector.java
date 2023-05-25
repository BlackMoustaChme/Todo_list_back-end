package rest.infrastructure.in.interconnector;


import jakarta.annotation.Resource;
import jakarta.ejb.MessageDriven;

import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.jms.*;

import jakarta.inject.Inject;


import rest.application.user.api.in.IUser;
import rest.application.user.implementation.UserModel;
import rest.infrastructure.builder.Built;

@TransactionManagement(TransactionManagementType.BEAN)
@MessageDriven(mappedName = "jms/SecondTestQueue")
public class Interconnector implements MessageListener{

    @Inject @Built
    private IUser userModel;

    @Resource(mappedName = "jms/WebAppFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/FirstTestQueue")
    private Queue firstQueue;

//    @Resource(mappedName = "jms/SecondTestQueue")
//    private Queue secondQueue;
//
    @Override
    public void onMessage(Message message) {
//        TextMessage textMessage = (TextMessage)message;
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
//            JMSConsumer consumer = context.createConsumer(secondQueue);
//            Message message = consumer.receive();
//            consumer.getMessageListener().onMessage();
            boolean isAuth = userModel.authUser(message.getStringProperty("login"),
                    message.getStringProperty("password"));
            Message newMessage = context.createMessage();
            newMessage.setBooleanProperty("Status", isAuth);
            producer.send(firstQueue, newMessage);
        } catch (JMSException e) {}
    }
}
