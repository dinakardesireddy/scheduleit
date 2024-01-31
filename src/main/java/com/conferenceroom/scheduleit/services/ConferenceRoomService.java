package com.conferenceroom.scheduleit.services;

import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.records.ConferenceRoomRecord;

import java.util.List;

public interface ConferenceRoomService {
    List<ConferenceRoomRecord> getAllRooms();
    List<ConferenceRoomRecord> findAvailableRooms(BookingRecord bookingsRecord);

    void bookingCapacityException(BookingRecord bookingRecord);
}
