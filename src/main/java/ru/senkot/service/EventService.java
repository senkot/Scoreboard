package ru.senkot.service;

import ru.senkot.model.Event;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class EventService {

    private List<Event> events;

    @Produces
    @Named
    public List<Event> getEvents() {
        return events;
    }

    public List<Event> eventsFromJson() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/test/" + new Date(Calendar.getInstance().getTime().getTime()));

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
            return event;
        }).collect(Collectors.toList());

        return events;
    }

    @PostConstruct
    public void setJsonEventsForJSF() {
        this.events = eventsFromJson();
    }

}
