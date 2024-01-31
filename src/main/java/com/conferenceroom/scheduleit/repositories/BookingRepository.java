package com.conferenceroom.scheduleit.repositories;

import com.conferenceroom.scheduleit.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
