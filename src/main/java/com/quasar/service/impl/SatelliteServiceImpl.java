package com.quasar.service.impl;

import com.quasar.Constants;
import com.quasar.domain.Satellite;
import com.quasar.domain.enums.SatelliteNameEnum;
import com.quasar.service.SatelliteService;
import com.quasar.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@SessionScope
@Service
public class SatelliteServiceImpl implements SatelliteService {
    List<Satellite> satellites;

    @Override
    public Satellite findByName(String name) {
        if (!SatelliteNameEnum.isMember(name))
            throw new NotFoundException(Constants.ENCODE_NAME_NOT_COMPLETE);

        if (null == satellites || satellites.size() == 0)
            throw new NotFoundException(Constants.SATELLITE_IS_EMPTY);

        return satellites.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Satellite> findAll() {
        return satellites;
    }

    @Override
    public void save(List<Satellite> satellites) {
        if (null == satellites || satellites.isEmpty() )
            throw new NotFoundException(Constants.SATELLITE_IS_EMPTY);
        for (Satellite satellite : satellites) {
            if (!SatelliteNameEnum.isMember(satellite.getName()))
                throw new NotFoundException(Constants.ENCODE_NAME_NOT_COMPLETE);

            if (null == satellite.getMessage() || satellite.getMessage().isEmpty() )
                throw new NotFoundException(Constants.ENCODE_MESSAGES_NOT_COMPLETE);

            if (null == satellite.getDistance() || satellite.getDistance() < 0)
                throw new NotFoundException(Constants.ENCODE_DISTANCE_NOT_COMPLETE);
        }
        this.satellites = satellites;
    }

    @Override
    public Satellite update(String name, Satellite satellite) {
        if (null == name)
            throw new NotFoundException(Constants.ENCODE_NAME_NOT_COMPLETE);
        satellite.setName(name.toLowerCase());
        return update(satellite);
    }

    @Override
    public Satellite update(Satellite satellite) {
        Satellite satelliteUpdated = findByName(satellite.getName());
        if (null == satelliteUpdated)
            throw new NotFoundException(Constants.SATELLITE_NOT_FOUND);

        satelliteUpdated.setName(satellite.getName());
        satelliteUpdated.setMessage(satellite.getMessage());
        satelliteUpdated.setDistance(satellite.getDistance());
        return satelliteUpdated;
    }
}
