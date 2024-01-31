package com.conferenceroom.scheduleit.validators;

import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.services.ConferenceRoomService;
import com.conferenceroom.scheduleit.services.MaintenanceService;
import com.conferenceroom.scheduleit.utils.EventScheduleValidatorUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NewBookingValidator implements ConstraintValidator<ValidNewBooking, BookingRecord> {
    private final ConferenceRoomService conferenceRoomService;
    private final MaintenanceService maintenanceService;

    public NewBookingValidator(ConferenceRoomService conferenceRoomService, MaintenanceService maintenanceService) {
        this.conferenceRoomService = conferenceRoomService;
        this.maintenanceService = maintenanceService;
    }

    @Override
    public boolean isValid(BookingRecord bookingRecord, ConstraintValidatorContext context) {
        EventScheduleValidatorUtil.isEndAfterStartException(bookingRecord.startTime(), bookingRecord.endTime());
        EventScheduleValidatorUtil.isIntervalValidException(bookingRecord.startTime(), bookingRecord.endTime());
        EventScheduleValidatorUtil.isTimeInPastException(bookingRecord.startTime(), bookingRecord.endTime());
        EventScheduleValidatorUtil.invalidNumberOfAttendeesException(bookingRecord.numberOfAttendees());
        // Booking cannot be done during maintenance time, if a booking overlaps maintenance a message should be displayed stating the same.
        maintenanceService.bookingOverlapsMaintenanceException(bookingRecord);
        // The number of people allowed for booking should be less than or equal to the maximum capacity.
        conferenceRoomService.bookingCapacityException(bookingRecord);
        return true;
    }
}
