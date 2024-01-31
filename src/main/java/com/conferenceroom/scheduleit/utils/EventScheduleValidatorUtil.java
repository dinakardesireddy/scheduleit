package com.conferenceroom.scheduleit.utils;

import com.conferenceroom.scheduleit.enums.ErrorCodesEnum;
import com.conferenceroom.scheduleit.exceptions.ConferenceAppException;

import java.time.Duration;
import java.time.LocalTime;

public class EventScheduleValidatorUtil {

    private EventScheduleValidatorUtil(){}
    public static void isEndAfterStartException(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new ConferenceAppException(ErrorCodesEnum.START_TIME_AFTER_END_TIME);
        }
    }

    public static void isIntervalValidException(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long minutesDiff = duration.toMinutes();
        if(minutesDiff == 0){
            throw new ConferenceAppException(ErrorCodesEnum.START_TIME_EQUALS_END_TIME);
        }
        if (minutesDiff % 15 != 0) {
            throw new ConferenceAppException(ErrorCodesEnum.INVALID_INTERVAL_LENGTH);
        }
    }

    public static void isTimeInPastException(LocalTime startTime, LocalTime endTime) {
        if (startTime.isBefore(LocalTime.now()) || endTime.isBefore(LocalTime.now())) {
            throw new ConferenceAppException(ErrorCodesEnum.TIME_IN_PAST);
        }
    }

    public static void invalidNumberOfAttendeesException(int numberOfAttendees){
        if (numberOfAttendees <= 0) {
            throw new ConferenceAppException(ErrorCodesEnum.COUNT_LESS_THAN_MIN);
        }
    }
}
