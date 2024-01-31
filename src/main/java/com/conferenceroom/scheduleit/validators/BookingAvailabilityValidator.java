package com.conferenceroom.scheduleit.validators;

import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.utils.EventScheduleValidatorUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookingAvailabilityValidator implements ConstraintValidator<ValidAvailability, BookingRecord> {

    @Override
    public boolean isValid(BookingRecord bookingsRecord, ConstraintValidatorContext context) {
        EventScheduleValidatorUtil.isEndAfterStartException(bookingsRecord.startTime(), bookingsRecord.endTime());
        EventScheduleValidatorUtil.isIntervalValidException(bookingsRecord.startTime(), bookingsRecord.endTime());
        return true;
    }
}
