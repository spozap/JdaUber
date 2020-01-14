package com.example.uberapp;

public class Message {

    String transmitter;
    String receiver;
    String message;
    String date;

    public Message(String transmitter, String receiver, String message, String date) {
        this.transmitter = transmitter;
        this.receiver = receiver;
        this.message = message;
        this.date = date;
    }

    public String getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(String transmitter) {
        this.transmitter = transmitter;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
