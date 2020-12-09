package com.quasar;

public class Constants {

    public static final String SPACESHIP_CONTROLLER_DESCRIPTION = "Operations pertaining to Quasar Fire Operation API";
    public static final String GET_SPACESHIP_DESCRIPTION = "Get Spaceship information given its satellites registered";
    public static final String CALCULATE_SPACESHIP_DESCRIPTION = "Calculate Spaceship information given its satellites";
    public static final String UPDATE_SPACESHIP_INFORMATION = "Update Spaceship information given its satellites";
    public static final String SPACESHIP_OBTAINED_DESCRIPTION = "Spaceship information successfully obtained";
    public static final String NOT_FOUND_RESPONSE_DESCRIPTION = "Could not determine the position and message of the spaceship";
    public static final String SATELLITE_IS_EMPTY = "Could not determine the position or the decode message of the spaceship because the satellites list is empty";
    public static final String SATELLITE_NOT_FOUND = "Could not update satellite information because not was found in the initial list.";
    public static final String ENCODE_NAME_NOT_COMPLETE = "Could not determine the position or the decode message because some satellite has not name";
    public static final String ENCODE_MESSAGES_NOT_COMPLETE = "Could not determine the correct message because some satellite has not a encode message";
    public static final String ENCODE_DISTANCE_NOT_COMPLETE = "Could not determine the correct position because some satellite has not a correct distance";
    public static final String NAME_REQUIRED_MESSAGE = "The name must be not null or not empty";
    public static final String DISTANCE_REQUIRED_MESSAGE = "The distance must be upper than zero";
    public static final String MESSAGE_REQUIRED_MESSAGE = "The message must be not null or not empty";
}