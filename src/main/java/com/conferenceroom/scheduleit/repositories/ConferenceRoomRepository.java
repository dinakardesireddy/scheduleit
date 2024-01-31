package com.conferenceroom.scheduleit.repositories;

import com.conferenceroom.scheduleit.models.ConferenceRoom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    @EntityGraph(attributePaths = "maintenanceTimeIntervals")
    List<ConferenceRoom> findAllByOrderByCapacityDesc();

    @Query("SELECT cr" +
            " FROM ConferenceRoom cr" +
            " WHERE cr.id NOT IN (" +
            "    SELECT b.conferenceRoom.id" +
            "    FROM Booking b" +
            "    WHERE" +
            "        (b.bookingDate = CURRENT_DATE AND :startTime >= b.startTime AND :startTime < b.endTime)" +
            "        OR" +
            "        (b.bookingDate = CURRENT_DATE AND :endTime > b.startTime AND :endTime <= b.endTime)" +
            ")" +
            " AND cr.id NOT IN (" +
            "    SELECT mi.conferenceRoom.id" +
            "    FROM MaintenanceInterval mi" +
            "    WHERE" +
            "        (:startTime >= mi.startTime AND :startTime < mi.endTime)" +
            "        OR" +
            "        (:endTime >= mi.startTime AND :endTime <= mi.endTime)" +
            ")")
    List<ConferenceRoom> findAvailableRooms(LocalTime startTime, LocalTime endTime);

}
