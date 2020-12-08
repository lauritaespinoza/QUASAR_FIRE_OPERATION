package com.quasar.domain;

public class Spaceship {
    private Position position;
    private String message;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "position=" + position +
                ", message='" + message + '\'' +
                '}';
    }
}
