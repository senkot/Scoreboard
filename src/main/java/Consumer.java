import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "Consumer",
activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/myQueue")
})
public class Consumer implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        try {
            System.out.println(textMessage.getText() + this.getClass().toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
