package com.conferenceroom.scheduleit.services.impl;

import com.conferenceroom.scheduleit.enums.ErrorCodesEnum;
import com.conferenceroom.scheduleit.exceptions.ConferenceAppException;
import com.conferenceroom.scheduleit.models.MaintenanceInterval;
import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.repositories.MaintenanceIntervalRepository;
import com.conferenceroom.scheduleit.services.MaintenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceIntervalRepository maintenanceIntervalRepository;

    public MaintenanceServiceImpl(MaintenanceIntervalRepository maintenanceIntervalRepository) {
        this.maintenanceIntervalRepository = maintenanceIntervalRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceServiceImpl.class);

    @Override
    public void bookingOverlapsMaintenanceException(BookingRecord bookingRecord) {
        List<MaintenanceInterval> conferenceRooms =  maintenanceIntervalRepository.findMaintenanceTimeByRoomId(
                bookingRecord.roomId(), bookingRecord.startTime(), bookingRecord.endTime());

        if(!conferenceRooms.isEmpty()) {
            logger.error("Booking overlaps maintenance {}", conferenceRooms);
            throw new ConferenceAppException(ErrorCodesEnum.BOOKING_OVERLAPS_MAINTENANCE);
        }
    }
}
