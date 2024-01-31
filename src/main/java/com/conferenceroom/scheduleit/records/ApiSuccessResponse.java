package com.conferenceroom.scheduleit.records;

public record ApiSuccessResponse<T>(boolean success, T data) {
}
