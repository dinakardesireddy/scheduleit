package com.conferenceroom.scheduleit.exceptions;

import com.conferenceroom.scheduleit.records.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConferenceAppException.class)
    public ResponseEntity<Object> handleConferenceAppExceptions(ConferenceAppException ex, WebRequest request) {
        return ResponseEntity.ok().body(new ApiErrorResponse(false, ex.getErrorCode(), ex.getErrorMessage()));
    }
}
