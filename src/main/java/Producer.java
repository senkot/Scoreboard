import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class Producer {

    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;

//    @Resource(lookup = "java:jboss/exported/jms/queue/myQueue")
//    private Destination destination;

    @Resource(mappedName = "java:jboss/exported/jms/queue/myQueue")
    Queue queue;

    public void produceMessage() {
        try {
//            Connection connection = connectionFactory.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer messageProducer = session.createProducer(destination);
//            TextMessage testMessage = session.createTextMessage("Test message");
//
//            messageProducer.send(testMessage);
//            System.out.println("-------------------------------------------");
//            connection.close();
//            session.close();

            JMSContext context = connectionFactory.createContext();
            context.createProducer().send(queue, "Hello, Queue!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
