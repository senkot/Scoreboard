package ru.senkot.model;

import java.io.Serializable;

public class Event implements Serializable {

    private String date;
    private String time;
    private String patient;
    private String remedy;
    private String type;
    private int quantity;
    private String status;

    public Event() {
    }

    public Event(String date, String time, String patient, String remedy, String type, int quantity, String status) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.remedy = remedy;
        this.type = type;
        this.quantity = quantity;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", patient='" + patient + '\'' +
                ", remedy='" + remedy + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
