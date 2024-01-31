package com.conferenceroom.scheduleit.repositories;

import com.conferenceroom.scheduleit.models.ConferenceRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConferenceRoomRepositoryTest {

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Test
    void findAllByOrderByCapacityDesc() {
        List<ConferenceRoom> conferenceRooms = conferenceRoomRepository.findAllByOrderByCapacityDesc();
        //then assert
        assertTrue(conferenceRooms.size() > 0);
    }

    @Test
    void findAvailableRooms() {
        List<ConferenceRoom> conferenceRooms = conferenceRoomRepository.findAvailableRooms(LocalTime.of(10,0),LocalTime.of(10,15));
        //then assert
        assertTrue(conferenceRooms.size() > 0);
    }
}