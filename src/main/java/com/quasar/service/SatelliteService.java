package com.quasar.service;

import com.quasar.domain.Satellite;

import java.util.List;

public interface SatelliteService {

    Satellite findByName(String name);

    List<Satellite> findAll();

    Satellite update(String name, Satellite satellites);

    Satellite update(Satellite satellites);

    void save(List<Satellite> satellites);
}
