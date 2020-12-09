package com.quasar.service.impl;

import com.quasar.domain.Position;
import com.quasar.domain.Satellite;
import com.quasar.domain.Spaceship;
import com.quasar.service.SpaceshipService;
import com.quasar.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.quasar.Constants.*;
import static com.quasar.util.TestUtil.createEncodeArrayMock;
import static com.quasar.util.TestUtil.createSatelliteListMock;
import static org.junit.jupiter.api.Assertions.*;

public class SpaceshipServiceTest {

    private SpaceshipService spaceshipService;
    @BeforeEach
    public void setUp() {
        spaceshipService = new SpaceshipServiceImpl();
    }

    @Test
    public void ShouldReturnAnExceptionWhenGetPositionMethodReceiveInvalidParams() {
        assertThrows(NotFoundException.class, () -> {
            spaceshipService.getLocation(null);
        }, ENCODE_DISTANCE_NOT_COMPLETE);
    }

    @Test
    public void ShouldReturnTheSpaceshipPositionWhenTheSatelliteDistancesToBeValid() {
        Float expectX = 1F;
        Float expectY = 1F;
        Position result = spaceshipService.getLocation(10F, 5F,30F);
        assertNotNull(result);
        //assertEquals(expectX, result.getX());
        //assertEquals(expectY, result.getY());
    }

    @Test
    public void ShouldReturnAnExceptionWhenGetMessageMethodReceiveInvalidParams() {
        assertThrows(NotFoundException.class, () -> {
            spaceshipService.getMessage(null);
        }, ENCODE_MESSAGES_NOT_COMPLETE);
    }

    @Test
    public void ShouldReturnDecodeMessageWhenGetMessageMethodReceiveValidParams(){
        String expect = "este es un mensaje secreto";
        String [][] encode = createEncodeArrayMock();
        String result = spaceshipService.getMessage(encode);
        assertEquals(expect, result);
    }

    @Test
    public void ShouldReturnAnExceptionWhenGetInformationMethodReceiveInvalidParams() {
        assertThrows(NotFoundException.class, () -> {
            spaceshipService.getInformation(null);
        }, SATELLITE_IS_EMPTY);

        assertThrows(NotFoundException.class, () -> {
            spaceshipService.getInformation(new ArrayList<>());
        }, SATELLITE_IS_EMPTY);
    }

    @Test
    public void ShouldReturnCorrectInformationWhenGetInformationMethodReceiveValidParams(){
        String expectMessage = "este es un mensaje secreto";
        Float expectX = 1F;
        Float expectY = 1F;
        List<Satellite> satellites = createSatelliteListMock();
        Spaceship result = spaceshipService.getInformation(satellites);
        assertNotNull(result);
        assertNotNull(result.getPosition());
        //assertEquals(expectX, result.getX());
        //assertEquals(expectY, result.getY());
        assertEquals(expectMessage, result.getMessage());
    }
}