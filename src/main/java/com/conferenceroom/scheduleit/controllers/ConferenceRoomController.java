package com.conferenceroom.scheduleit.controllers;

import com.conferenceroom.scheduleit.records.ApiSuccessResponse;
import com.conferenceroom.scheduleit.records.BookingRecord;
import com.conferenceroom.scheduleit.services.ConferenceRoomService;
import com.conferenceroom.scheduleit.utils.CommonUtils;
import com.conferenceroom.scheduleit.validators.ValidAvailability;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conference-rooms")
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @PostMapping("/check-availability")
    public ResponseEntity<ApiSuccessResponse<Object>> getAvailableConferenceRooms(@RequestBody @ValidAvailability BookingRecord bookingsRecord) {
        return ResponseEntity.ok(CommonUtils.buildResponse(conferenceRoomService.findAvailableRooms(bookingsRecord)));
    }

}
