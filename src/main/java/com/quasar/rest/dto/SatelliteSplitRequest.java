package com.quasar.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.quasar.Constants.DISTANCE_REQUIRED_MESSAGE;
import static com.quasar.Constants.MESSAGE_REQUIRED_MESSAGE;

public class SatelliteSplitRequest {
    @Min(value = 0, message = DISTANCE_REQUIRED_MESSAGE)
    private Float distance;
    @NotNull(message = MESSAGE_REQUIRED_MESSAGE)
    private List<String> message;

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
        return "SatelliteSplitRequest{" +
                "distance=" + distance +
                ", message=" + message +
                '}';
    }
}