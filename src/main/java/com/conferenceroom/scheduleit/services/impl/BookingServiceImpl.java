package com.conferenceroom.scheduleit.services.impl;

import com.conferenceroom.scheduleit.enums.ErrorCodesEnum;
import com.conferenceroom.scheduleit.exceptions.ConferenceAppException;
import com.conferenceroom.scheduleit.models.Booking;
import com.conferenceroom.scheduleit.models.ConferenceRoom;
import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.repositories.BookingRepository;
import com.conferenceroom.scheduleit.repositories.ConferenceRoomRepository;
import com.conferenceroom.scheduleit.services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, ConferenceRoomRepository conferenceRoomRepository) {
        this.bookingRepository = bookingRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final ReentrantLock lock = new ReentrantLock();


    @Override
    public BookingRecord bookConferenceRoom(BookingRecord bookingRecord) {
        lock.lock();
        try {
            logger.info("Saving a new booking {}", bookingRecord);
            isBookableElseStop(bookingRecord);
            bookingRepository.save(new Booking(bookingRecord));
        } finally {
            lock.unlock();
        }
        return bookingRecord;
    }

    @Override
    public void isBookableElseStop(BookingRecord bookingRecord) {
        List<ConferenceRoom> rooms = conferenceRoomRepository.findAvailableRooms(bookingRecord.startTime(), bookingRecord.endTime());
        if(rooms.stream().noneMatch(room -> room.getId().equals(bookingRecord.roomId()))) {
            throw new ConferenceAppException(ErrorCodesEnum.ROOM_NOT_AVAILABLE);
        }
    }
}
