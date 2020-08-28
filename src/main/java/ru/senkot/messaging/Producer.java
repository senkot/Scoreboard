package ru.senkot.messaging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class Producer {

    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:jboss/exported/jms/queue/myQueue")
    private Destination destination;

    public void produceMessage() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage testMessage = session.createTextMessage("Test message");

            messageProducer.send(testMessage);
            System.out.println("-------------------------------------------");
            connection.close();
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
