package com.quasar.service;

import com.quasar.domain.Position;
import com.quasar.domain.Satellite;
import com.quasar.domain.Spaceship;

import java.util.List;

public interface SpaceshipService {

    Spaceship getInformation(List<Satellite> satellites);

    Position getLocation(float... distance);

    String getMessage(String[]... messages);
}