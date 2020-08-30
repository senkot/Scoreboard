package ru.senkot.messaging;

import ru.senkot.service.EventService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.websocket.server.ServerEndpoint;

@MessageDriven(name = "consumer",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = "java:jboss/exported/jms/queue/myQueue")
        })
public class Consumer implements MessageListener {

    @EJB
    EventService eventService;

    @Inject
    @Push(channel = "channel")
    private PushContext pushContext;


    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

        eventService.setJsonEventsForJSF();
        System.out.println("Events injected into EventService from JSON");


        pushContext.send("channel");

    }
}
