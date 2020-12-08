package com.quasar.rest.controller;

import com.quasar.Constants;
import com.quasar.domain.Satellite;
import com.quasar.domain.Spaceship;
import com.quasar.rest.dto.SatelliteRequest;
import com.quasar.service.SatelliteService;
import com.quasar.service.SpaceshipService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static com.quasar.Constants.*;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "SpaceshipController", description = SPACESHIP_CONTROLLER_DESCRIPTION)
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    @Autowired
    private SatelliteService satelliteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceshipController.class);

    @Operation(summary = CALCULATE_SPACESHIP_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SPACESHIP_OBTAINED_DESCRIPTION),
            @ApiResponse(responseCode = "404", description = NOT_FOUND_RESPONSE_DESCRIPTION, content = @Content) })
    @PostMapping("/topsecret")
    public ResponseEntity<Spaceship> calculateSpaceshipInformation(@RequestBody @Valid SatelliteRequest satellites){
        LOGGER.info("Invoking SpaceshipController getSpaceshipInformation endpoint");
        satelliteService.save(satellites.getSatellites());
        Spaceship spaceship = spaceshipService.getInformation(satelliteService.findAll());
        return new ResponseEntity<>(spaceship, HttpStatus.OK);
    }

    @Operation(summary = Constants.UPDATE_SPACESHIP_INFORMATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constants.SPACESHIP_OBTAINED_DESCRIPTION),
            @ApiResponse(responseCode = "404", description = Constants.NOT_FOUND_RESPONSE_DESCRIPTION, content = @Content) })
    @PostMapping("/topsecret_split/{satelliteName}")
    public ResponseEntity<Spaceship> updateSpaceshipInformation(@PathVariable String satelliteName, @RequestBody @Valid Satellite satellite){
        LOGGER.info("Invoking SpaceshipController updateSpaceshipInformation endpoint");
        satelliteService.update(satelliteName, satellite);
        Spaceship spaceship = spaceshipService.getInformation(satelliteService.findAll());
        return new ResponseEntity<>(spaceship, HttpStatus.OK);
    }

    @Operation(summary = Constants.GET_SPACESHIP_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constants.SPACESHIP_OBTAINED_DESCRIPTION),
            @ApiResponse(responseCode = "404", description = Constants.NOT_FOUND_RESPONSE_DESCRIPTION, content = @Content) })
    @GetMapping("/topsecret_split/{satelliteName}")
    public ResponseEntity<Spaceship> getSpaceshipInformation(@PathVariable String satelliteName){
        LOGGER.info("Invoking SpaceshipController getSpaceshipInformation endpoint");
        Spaceship spaceship = spaceshipService.getInformation(satelliteService.findAll());
        return new ResponseEntity<>(spaceship, HttpStatus.OK);
    }

}