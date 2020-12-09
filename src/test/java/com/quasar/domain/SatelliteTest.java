package com.quasar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

import static com.quasar.Constants.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SatelliteTest {
    private Satellite satellite;
    private Validator validator;

    private static final String NAME_PROPERTY = "name";
    private static final String DISTANCE_PROPERTY = "distance";
    private static final String MESSAGE_PROPERTY = "message";

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        satellite = createSatelliteMock();
    }

    @Test
    public void checkNotNullSpaceshipName() {
        satellite.setName(null);
        checkValidations(validator.validate(satellite), NAME_REQUIRED_MESSAGE, NAME_PROPERTY, null);
    }

    @Test
    public void checkNotEmptySpaceshipName() {
        satellite.setName(EMPTY);
        checkValidations(validator.validate(satellite), NAME_REQUIRED_MESSAGE, NAME_PROPERTY, EMPTY);
    }

    @Test
    public void checkNotNullSpaceshipMessage() {
        satellite.setMessage(null);
        checkValidations(validator.validate(satellite), MESSAGE_REQUIRED_MESSAGE, MESSAGE_PROPERTY, null);
    }

    @Test
    public void checkNotNullSpaceshipDistance() {
        Float number = Float.valueOf(-1);
        satellite.setDistance(number);
        checkValidations(validator.validate(satellite), DISTANCE_REQUIRED_MESSAGE, DISTANCE_PROPERTY, number);
    }

    private void checkValidations(Set<ConstraintViolation<Satellite>> violations, String errorMessage, String propertyName, Object value) {
        ConstraintViolation<Satellite> violation = violations.iterator().next();
        assertEquals(errorMessage, violation.getMessage());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(value, violation.getInvalidValue());
    }

    private Satellite createSatelliteMock(){
        Satellite satellite = new Satellite();
        satellite.setName("sato");
        satellite.setDistance(Float.parseFloat("142F"));
        satellite.setMessage(Arrays.asList("this","is","a","messages"));

        return satellite;
    }

}