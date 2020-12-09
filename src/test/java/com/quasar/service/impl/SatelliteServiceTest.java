package com.quasar.service.impl;

import com.quasar.domain.Satellite;
import com.quasar.service.SatelliteService;
import com.quasar.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.quasar.Constants.ENCODE_DISTANCE_NOT_COMPLETE;
import static com.quasar.Constants.ENCODE_MESSAGES_NOT_COMPLETE;
import static com.quasar.Constants.ENCODE_NAME_NOT_COMPLETE;
import static com.quasar.Constants.SATELLITE_IS_EMPTY;
import static com.quasar.util.TestUtil.createSatelliteListMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SatelliteServiceTest {
    private SatelliteService satelliteService;

    @BeforeEach
    public void setUp() {
        satelliteService = new SatelliteServiceImpl();
    }

    @Test
    public void ShouldReturnAnExceptionWhenSaveMethodReceiveInvalidParms() {
        assertThrows(NotFoundException.class, () -> {
            satelliteService.save(null);
        }, SATELLITE_IS_EMPTY);

        assertThrows(NotFoundException.class, () -> {
            satelliteService.save(new ArrayList<>());
        }, SATELLITE_IS_EMPTY);
    }

    @Test
    public void ShouldReturnAnExceptionWhenSaveMethodReceiveASatelliteWithInvalidData() {
        assertThrows(NotFoundException.class, () -> {
            List<Satellite> satellites = Arrays.asList(new Satellite("apolo11", Float.valueOf("100.0"), Arrays.asList("")));
            satelliteService.save(satellites);
        }, ENCODE_NAME_NOT_COMPLETE);

        assertThrows(NotFoundException.class, () -> {
            List<Satellite> satellites =  Arrays.asList(new Satellite("kenobi", Float.valueOf("100.0"), Arrays.asList("")));
            satelliteService.save(new ArrayList<>());
        }, ENCODE_MESSAGES_NOT_COMPLETE);

        assertThrows(NotFoundException.class, () -> {
            List<Satellite> satellites = Arrays.asList(new Satellite("kenobi", Float.valueOf("-100.0"), Arrays.asList("un", "mensaje")));
            satelliteService.save(satellites);
        }, ENCODE_DISTANCE_NOT_COMPLETE);
    }

    @Test
    public void ShouldPersistCorrectlyTheListWhenTheSaveMethodReceiveSatellitesWithValidData() {
        List<Satellite> satellites = createSatelliteListMock();
        satelliteService.save(satellites);
        List<Satellite> result = satelliteService.findAll();
        assertEquals(satellites, result);
    }

    @Test
    public void ShouldUpdateAnItemOfListWhenTheUpdateMethodReceiveValidArguments(){
        List<Satellite> satellites = createSatelliteListMock();
        Satellite selected = satellites.get(1);
        selected.setMessage(Arrays.asList("es", "un", "mensaje", "nuevo"));
        selected.setDistance(Float.parseFloat("21.4F"));
        satelliteService.save(satellites);
        Satellite updated = satelliteService.update(selected.getName(), selected);
        assertNotNull(updated);
        assertEquals(selected.getMessage(), updated.getMessage());
        assertEquals(selected.getDistance(), updated.getDistance());
    }
}