package com.conferenceroom.scheduleit.controllers;

import com.conferenceroom.scheduleit.records.ApiSuccessResponse;
import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.services.BookingService;
import com.conferenceroom.scheduleit.utils.CommonUtils;
import com.conferenceroom.scheduleit.validators.ValidNewBooking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/submit-new")
    public ResponseEntity<ApiSuccessResponse<Object>> bookConferenceRoom(@RequestBody @ValidNewBooking BookingRecord bookingRecord) {
        logger.info("New booking request {} ", bookingRecord);
        return ResponseEntity.ok(CommonUtils.buildResponse(bookingService.bookConferenceRoom(bookingRecord)));

    }
}
