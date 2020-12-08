package com.quasar.domain;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Satellite {
    @NotBlank(message = "The name must be not null or not empty")
    private String name;
    @NotBlank(message = "The distance must be upper than zero")
    private Float distance;
    @NotBlank(message = "The message must be not null or not empty")
    private List<String> message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", message=" + message +
                '}';
    }
}
