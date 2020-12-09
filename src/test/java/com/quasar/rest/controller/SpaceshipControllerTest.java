package com.quasar.rest.controller;

import com.quasar.domain.Satellite;
import com.quasar.domain.Spaceship;
import com.quasar.rest.dto.SatelliteRequest;
import com.quasar.service.SatelliteService;
import com.quasar.service.SpaceshipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.quasar.util.TestUtil.asJsonString;
import static com.quasar.util.TestUtil.createSpaceshipMock;
import static com.quasar.util.TestUtil.createSatelliteListMock;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(value = SpaceshipController.class)
@ComponentScan(basePackages = {"com.quasar"})
class SpaceshipControllerTest {

    @MockBean
    private SpaceshipService snapshipServiceMock;

    @MockBean
    private SatelliteService satelliteServiceMock;

    @Autowired
    private MockMvc mockMvc;

    private static final String TOPSECRET_ENDPOINT = "/api/v1/topsecret";
    private static final String TOPSECRET_SPLIT_ENDPOINT = "/api/v1/topsecret_split";


    @Test
    public void calculateSpaceshipInformation() throws Exception {
        Spaceship spaceship = createSpaceshipMock();
        List satellites = createSatelliteListMock();

        SatelliteRequest request = new SatelliteRequest();
        request.setSatellites(satellites);

        when(satelliteServiceMock.findAll()).thenReturn(satellites);
        when(snapshipServiceMock.getInformation(satellites)).thenReturn(spaceship);

        mockMvc.perform(post(TOPSECRET_ENDPOINT)
                .content(asJsonString(request))
                .contentType(APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(is(spaceship.getPosition().getX()), Float.class))
                .andExpect(jsonPath("$.position.y").value(is(spaceship.getPosition().getY()), Float.class))
                .andExpect(jsonPath("$.message", is(spaceship.getMessage())))
                .andDo(print());
    }

    @Test
    void updateSpaceshipInformation() throws Exception {
        Spaceship spaceship = createSpaceshipMock();
        List satellites = createSatelliteListMock();
        String nameSatellite = "skywalker";
        Satellite satellite = new Satellite(nameSatellite, Float.valueOf("100.0"), Arrays.asList("este","","","mensaje","") );

        when(satelliteServiceMock.findAll()).thenReturn(satellites);
        when(snapshipServiceMock.getInformation(satellites)).thenReturn(spaceship);

        mockMvc.perform(post(TOPSECRET_SPLIT_ENDPOINT + "/" + nameSatellite)
                .content(asJsonString(satellite))
                .contentType(APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(is(spaceship.getPosition().getX()), Float.class))
                .andExpect(jsonPath("$.position.y").value(is(spaceship.getPosition().getY()), Float.class))
                .andExpect(jsonPath("$.message", is(spaceship.getMessage())))
                .andDo(print());
    }

    @Test
    void getSpaceshipInformation() throws Exception {
        Spaceship spaceship = createSpaceshipMock();
        List satellites = createSatelliteListMock();
        when(satelliteServiceMock.findAll()).thenReturn(satellites);
        when(snapshipServiceMock.getInformation(satellites)).thenReturn(spaceship);

        mockMvc.perform(get(TOPSECRET_SPLIT_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(is(spaceship.getPosition().getX()), Float.class))
                .andExpect(jsonPath("$.position.y").value(is(spaceship.getPosition().getY()), Float.class))
                .andExpect(jsonPath("$.message", is(spaceship.getMessage())))
                .andDo(print());
    }
}