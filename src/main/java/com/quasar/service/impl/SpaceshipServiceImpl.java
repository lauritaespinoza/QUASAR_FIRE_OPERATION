package com.quasar.service.impl;

import com.quasar.Constants;
import com.quasar.domain.Position;
import com.quasar.domain.Satellite;
import com.quasar.domain.Spaceship;
import com.quasar.service.SpaceshipService;
import com.quasar.service.exception.NotFoundException;
import com.quasar.service.util.MesaggeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceshipServiceImpl.class);

    @Override
    public Spaceship getInformation(List<Satellite> satellites) {
        LOGGER.info("Getting location and message from spaceship");
        if (null == satellites || satellites.isEmpty())
            throw new NotFoundException(Constants.SATELLITE_IS_EMPTY);

        List<List<String>> encodeList = new ArrayList<>();
        int maxSize = 0;

        //obtains the encode message from all satellites and take the max size
        for (Satellite satellite : satellites) {
            encodeList.add(satellite.getMessage());
            int size = satellite.getMessage().size();
            if (maxSize < size) maxSize = size;
        }

        //transform encodeList to array
        String[][] arrayMessages = MesaggeUtils.transformToArray2D(encodeList, maxSize);
        //decodes the message
        String decodeMessage = getMessage(arrayMessages);
        //obtains the position
        Position position = getLocation(1F, 5F, 2F);

        Spaceship spaceship = new Spaceship();
        spaceship.setPosition(position);
        spaceship.setMessage(decodeMessage);

        return spaceship;
    }

    @Override
    public Position getLocation(float... distance){
        if (null == distance)
            throw new NotFoundException(Constants.ENCODE_DISTANCE_NOT_COMPLETE);

        Position position = new Position();
        float leftLimit = 1F;
        float rightLimit = 10F;
        position.setX(leftLimit + new Random().nextFloat() * (rightLimit - leftLimit));
        position.setY(leftLimit + new Random().nextFloat() * (rightLimit - leftLimit));
        return position;
    }

    @Override
    public String getMessage(String[]... messages){
        if (null == messages)
            throw new NotFoundException(Constants.ENCODE_MESSAGES_NOT_COMPLETE);

        List<String> wordList = new ArrayList();

        //reverse the array2D of messages encoded
        MesaggeUtils.reverseArray(messages);

        //takes each item of the array 2D in the same position and selects which word is added to message decoded
        for (int j = 0; j < messages[0].length ; j++){
            List<String> words = new ArrayList<>();
            for (int i = 0; i < messages.length ; i++){
                if (j < messages[i].length)
                    words.add(messages[i][j]);
            }
            String word = MesaggeUtils.selectWord(words);
            if (word == null) continue;
            wordList.add(word);
        }

        //reverse the items of decode message
        Collections.reverse(wordList);

        //creates a string with the message decoded
        return String.join(" ", wordList);
    }
}