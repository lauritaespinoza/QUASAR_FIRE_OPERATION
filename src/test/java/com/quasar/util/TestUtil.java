package com.quasar.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.domain.Position;
import com.quasar.domain.Satellite;
import com.quasar.domain.Spaceship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static Spaceship createSpaceshipMock(){
        Spaceship spaceship = new Spaceship();
        spaceship.setMessage("este es un mensaje secreto");
        spaceship.setPosition(new Position(Float.valueOf("8.961981"), Float.valueOf("7.023014")));
        return spaceship;
    }

    public static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    public static List<Satellite> createSatelliteListMock(){
        List<Satellite> satellites = new ArrayList<>();

        Satellite kenobi = new Satellite("kenobi", Float.valueOf("100.0"), Arrays.asList("este","","","mensaje",""));
        Satellite skywalker = new Satellite("skywalker", Float.valueOf("115.5"), Arrays.asList("","es","","","secreto"));
        Satellite sato = new Satellite("sato", Float.valueOf("142.7"), Arrays.asList("este","","un","",""));

        for (Satellite satellite : Arrays.asList(kenobi, skywalker, sato)) {
            satellites.add(satellite);
        }

        return satellites;
    }

    public static String[][] createEncodeArrayMock(){
        String[][] encode = new String[3][];
        encode[0] = new String[]{"este", "", "", "mensaje", ""};
        encode[1] = new String[]{"", "es", "", "", "secreto"};
        encode[2] = new String[]{"este", "", "un", "", ""};
        return encode;
    }
}