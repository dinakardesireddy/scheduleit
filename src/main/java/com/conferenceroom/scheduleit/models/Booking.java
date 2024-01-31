package com.conferenceroom.scheduleit.models;

import com.conferenceroom.scheduleit.records.BookingRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_room_id", nullable = false)
    private ConferenceRoom conferenceRoom;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private int numberOfAttendees;

    @PrePersist
    public void prePersist() {
            bookingDate = LocalDate.now();
    }

    // Constructor to copy properties from BookingRecord
    public Booking(BookingRecord bookingRecord) {
        ConferenceRoom conferenceRoomToBook = new ConferenceRoom();
        conferenceRoomToBook.setId(bookingRecord.roomId());
        this.conferenceRoom = conferenceRoomToBook;
        this.startTime = bookingRecord.startTime();
        this.endTime = bookingRecord.endTime();
    }

    public Booking() {
    }
}
