package com.conferenceroom.scheduleit.services.impl;

import com.conferenceroom.scheduleit.models.ConferenceRoom;
import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.records.ConferenceRoomRecord;
import com.conferenceroom.scheduleit.repositories.ConferenceRoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConferenceRoomServiceImplTest {

    @InjectMocks
    private ConferenceRoomServiceImpl conferenceRoomServiceImpl;
    @Mock
    private ConferenceRoomRepository conferenceRoomRepository;
    private List<ConferenceRoom> availableRooms;

    @BeforeEach
    void setUp() {
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setId(1L);
        conferenceRoom.setName("Amaze");
        conferenceRoom.setCapacity(5);
        availableRooms = List.of(conferenceRoom);
    }

    @AfterEach
    void tearDown() {
        availableRooms = null;
    }

    @Test
    void testFindAvailableRooms_returnsRoomsWhenAvailable() {
        // Given
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.plusMinutes(15);
        BookingRecord bookingRecord = new BookingRecord(null,startTime, endTime,0);

        // when
        when(conferenceRoomRepository.findAvailableRooms(startTime, endTime)).thenReturn(availableRooms);

        //then
        List<ConferenceRoomRecord> foundRoomRecords = conferenceRoomServiceImpl.findAvailableRooms(bookingRecord);
        assertTrue(foundRoomRecords.size() > 0);
    }
}