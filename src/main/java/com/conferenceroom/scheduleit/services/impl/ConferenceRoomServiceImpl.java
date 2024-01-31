package com.conferenceroom.scheduleit.services.impl;

import com.conferenceroom.scheduleit.enums.ErrorCodesEnum;
import com.conferenceroom.scheduleit.exceptions.ConferenceAppException;
import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.records.ConferenceRoomRecord;
import com.conferenceroom.scheduleit.records.MaintenanceIntervalRecord;
import com.conferenceroom.scheduleit.models.ConferenceRoom;
import com.conferenceroom.scheduleit.repositories.ConferenceRoomRepository;
import com.conferenceroom.scheduleit.services.ConferenceRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceRoomServiceImpl implements ConferenceRoomService {
    private static final Logger logger = LoggerFactory.getLogger(ConferenceRoomServiceImpl.class);

    private final ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceRoomServiceImpl(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }
    @Cacheable("roomsCache")
    @Override
    public List<ConferenceRoomRecord> getAllRooms() {
        List<ConferenceRoom> conferenceRooms = conferenceRoomRepository.findAllByOrderByCapacityDesc();
        return conferenceRooms
                .stream()
                .map(room -> new ConferenceRoomRecord(
                        room.getId(),
                        room.getName(),
                        room.getCapacity(),
                        room.getMaintenanceTimeIntervals()
                                .stream()
                                .map(interval -> new MaintenanceIntervalRecord(
                                        interval.getId(),
                                        interval.getStartTime(),
                                        interval.getEndTime()))
                                .toList())).toList();
    }

    @Override
    public List<ConferenceRoomRecord> findAvailableRooms(BookingRecord bookingsRecord) {
        List<ConferenceRoom> conferenceRooms =  conferenceRoomRepository.findAvailableRooms(bookingsRecord.startTime(), bookingsRecord.endTime());
        logger.info("Available conference rooms {}", conferenceRooms);
        return conferenceRooms
                .stream()
                .map(room -> new ConferenceRoomRecord(
                        room.getId(),
                        room.getName(),
                        room.getCapacity(),
                        null)).toList();
    }

    @Override
    public void bookingCapacityException(BookingRecord bookingRecord) {
        Optional<ConferenceRoomRecord> conferenceRoomDto = getAllRooms().stream().filter(room -> room.id().equals(bookingRecord.roomId())).findFirst();
        if(conferenceRoomDto.isPresent() && bookingRecord.numberOfAttendees() > conferenceRoomDto.get().capacity()){
            throw new ConferenceAppException(ErrorCodesEnum.COUNT_EXCEEDS_MAX);
        }
    }
}
