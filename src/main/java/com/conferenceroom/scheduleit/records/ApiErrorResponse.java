package com.conferenceroom.scheduleit.records;

public record ApiErrorResponse(boolean success, String errorCode, String errorMessage) {
}
