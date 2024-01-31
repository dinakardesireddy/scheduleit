package com.conferenceroom.scheduleit.repositories;

import com.conferenceroom.scheduleit.models.MaintenanceInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
@Repository
public interface MaintenanceIntervalRepository extends JpaRepository<MaintenanceInterval, Long> {
    @Query("SELECT mi" +
            " FROM MaintenanceInterval mi" +
            " WHERE" +
            "    mi.conferenceRoom.id = :id" +
            "    AND (" +
            "        (:startTime >= mi.startTime AND :startTime < mi.endTime)" +
            "        OR (:endTime >= mi.startTime AND :endTime <= mi.endTime)" +
            "    )")
    List<MaintenanceInterval> findMaintenanceTimeByRoomId(Long id, LocalTime startTime, LocalTime endTime);
}
