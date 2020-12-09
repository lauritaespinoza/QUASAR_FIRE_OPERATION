package com.quasar.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import java.util.List;

import static com.quasar.Constants.DISTANCE_REQUIRED_MESSAGE;
import static com.quasar.Constants.MESSAGE_REQUIRED_MESSAGE;
import static com.quasar.Constants.NAME_REQUIRED_MESSAGE;

public class Satellite {
    @NotBlank(message = NAME_REQUIRED_MESSAGE)
    private String name;
    @Min(value = 0, message = DISTANCE_REQUIRED_MESSAGE)
    private Float distance;
    @NotNull(message = MESSAGE_REQUIRED_MESSAGE)
    private List<String> message;

    public Satellite(){}

    public Satellite(@NotBlank(message = NAME_REQUIRED_MESSAGE) String name, @Min(value = 0, message = DISTANCE_REQUIRED_MESSAGE) Float distance, @NotNull(message = MESSAGE_REQUIRED_MESSAGE) List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

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
