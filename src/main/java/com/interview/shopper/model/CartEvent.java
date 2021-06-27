package com.interview.shopper.model;

public enum CartEvent {
    ADD("ADD"),
    REMOVE("REMOVE");

    private String event;

    CartEvent(String event) {
        this.event = event;
    }
}
