package com.conferenceroom.scheduleit.controllers;

import com.conferenceroom.scheduleit.records.ConferenceRoomRecord;
import com.conferenceroom.scheduleit.services.ConferenceRoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ConferenceRoomController.class)
class ConferenceRoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ConferenceRoomService conferenceRoomService;

    @Test
    void shouldReturnAvailableConferenceRoomsForGivenTimeRange() throws Exception {
        var availabilityRequest = "{\"startTime\": \"20:15:00\", \"endTime\": \"20:30:00\"}";
        //given
        List<ConferenceRoomRecord> mockAvailableRooms = Arrays.asList(
                new ConferenceRoomRecord(1L, "Amaze", 5, null),
                new ConferenceRoomRecord(2L, "Inspire", 10, null),
                new ConferenceRoomRecord(3L, "Strive", 15, null)
        );
        //when
        when(conferenceRoomService.findAvailableRooms(Mockito.any())).thenReturn(mockAvailableRooms);
        //then
        mockMvc.perform(post("/api/v1/conference-rooms/check-availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(availabilityRequest))
                        .andExpectAll(
                                status().isOk()
                                ,jsonPath("$.data[0].name", is("Amaze")));
    }

    @Test
    void shouldReturnErrorMessageWhenStartTimeEqualsEndTime() throws Exception {
        var availabilityRequest = "{\"startTime\": \"20:15:00\", \"endTime\": \"20:15:00\"}";

        mockMvc.perform(post("/api/v1/conference-rooms/check-availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(availabilityRequest))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("errorMessage", is("Start time cannot be equal to end time")));
    }

    @Test
    void shouldReturnErrorMessageWhenBookingIntervalIsIncorrect() throws Exception {
        var availabilityRequest = "{\n" +
                "  \"startTime\": \"20:15:00\",\n" +
                "  \"endTime\": \"20:16:00\"\n" +
                "}";
        mockMvc.perform(post("/api/v1/conference-rooms/check-availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(availabilityRequest))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("errorMessage", is("Interval must be a multiple of 15 minutes")));
    }

}