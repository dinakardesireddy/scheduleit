package com.conferenceroom.scheduleit.records;

import java.time.LocalTime;

public record BookingRecord(Long roomId, LocalTime startTime, LocalTime endTime, int numberOfAttendees) {
}
