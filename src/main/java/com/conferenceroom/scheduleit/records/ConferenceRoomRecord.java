package com.conferenceroom.scheduleit.records;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record ConferenceRoomRecord(Long id, String name, int capacity, @JsonIgnore List<MaintenanceIntervalRecord> maintenanceIntervalRecords) {}

