package com.quasar.rest.dto;

import com.quasar.domain.Satellite;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class SatelliteRequest{
    @NotBlank(message = "The satellite list must be not null or not empty")
    private List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}