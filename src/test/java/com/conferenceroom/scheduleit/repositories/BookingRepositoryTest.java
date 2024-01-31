package com.conferenceroom.scheduleit.repositories;

import com.conferenceroom.scheduleit.models.Booking;
import com.conferenceroom.scheduleit.records.BookingRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        bookingRepository.deleteAll();
    }

    @Test
    void shouldSaveNewBooking(){
        //Arrange
        BookingRecord bookingRecord = new BookingRecord(1L, LocalTime.of(10,10), LocalTime.of(10,30),5);
        Booking bookingToSave = new Booking(bookingRecord);

        //Act
        bookingRepository.save(bookingToSave);
        Optional<Booking> fromDb = bookingRepository.findById(1L);

        //then assert
        assertTrue(fromDb.isPresent());
    }
}