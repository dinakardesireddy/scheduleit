package com.conferenceroom.scheduleit.services;

import com.conferenceroom.scheduleit.records.BookingRecord;

public interface MaintenanceService {
    void bookingOverlapsMaintenanceException(BookingRecord bookingRecord);
}
