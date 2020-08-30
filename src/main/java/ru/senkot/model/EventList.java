package ru.senkot.model;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


public class EventList {

    private List<Event> events;

    public EventList() {
    }

    @Override
    public String toString() {
        return "EventList{" +
                "events = " + events.size() +
                " shtuki}";
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
