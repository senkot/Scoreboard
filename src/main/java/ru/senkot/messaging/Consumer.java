package ru.senkot.messaging;

import ru.senkot.model.Event;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@MessageDriven(name = "Consumer",
activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/myQueue")
})
public class Consumer implements MessageListener {


    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        try {
            System.out.println(textMessage.getText() + "  " + this.getClass().toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/test/" + new Date(Calendar.getInstance().getTime().getTime()));

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        Response response = target.request().get();

        JsonArray jsonArray = response.readEntity(JsonArray.class);

        List<Event> events = jsonArray.stream().map(eventJson -> {
            Event event = new Event(
                    ((JsonObject) eventJson).getString("date"),
                    ((JsonObject) eventJson).getString("time"),
                    ((JsonObject) eventJson).getString("patient"),
                    ((JsonObject) eventJson).getString("remedy"),
                    ((JsonObject) eventJson).getString("type"),
                    ((JsonObject) eventJson).getInt("quantity"),
                    ((JsonObject) eventJson).getString("status"));
            return event;}).collect(Collectors.toList());

        for (Event event : events) {
            System.out.println(event.toString());
        }

    }
}
