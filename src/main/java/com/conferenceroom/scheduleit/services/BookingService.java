package com.conferenceroom.scheduleit.services;

import com.conferenceroom.scheduleit.records.BookingRecord;

public interface BookingService {
    BookingRecord bookConferenceRoom(BookingRecord bookingRecord);
    void isBookableElseStop(BookingRecord bookingRecord);
}
