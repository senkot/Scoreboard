package ru.senkot.service;

import ru.senkot.model.Event;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Singleton
public class EventService {

    @Inject
    @Push
    private PushContext push;

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
        List<Event> events = Arrays.asList(response.readEntity(Event[].class));

        return events;
    }

    @PostConstruct
    public void setJsonEventsForJSF() {
        this.events = eventsFromJson();
        push.send("push");
    }

}
