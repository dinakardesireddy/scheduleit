package com.conferenceroom.scheduleit.enums;

public enum ErrorCodesEnum {
    START_TIME_AFTER_END_TIME("INVALID_TIME_1001", "Start time cannot be after end time"),
    INVALID_INTERVAL_LENGTH("INVALID_TIME_1003", "Interval must be a multiple of 15 minutes"),
    TIME_IN_PAST("INVALID_TIME_1004", "Cannot book for time in past"),
    START_TIME_EQUALS_END_TIME("INVALID_TIME_1004", "Start time cannot be equal to end time"),
    COUNT_LESS_THAN_MIN("INVALID_PEOPLE_COUNT_1001", "Number of attendees must be greater than 1"),
    COUNT_EXCEEDS_MAX("INVALID_PEOPLE_COUNT_1002", "Number of attendees cannot exceed room capacity"),
    ROOM_NOT_AVAILABLE("ROOM_NOT_AVAILABLE", "This room is not available for your booking time"),
    GENERIC_EXCEPTION("GENERIC_EXCEPTION", "Unable to process the request"),
    BOOKING_OVERLAPS_MAINTENANCE("BOOKING_OVERLAPS_MAINTENANCE", "Booking overlaps with maintenance time. Please choose another time.");


    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    ErrorCodesEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}