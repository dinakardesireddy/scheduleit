package com.conferenceroom.scheduleit.records;

import java.time.LocalTime;

public record MaintenanceIntervalRecord(Long id, LocalTime startTime, LocalTime endTime) {
}
