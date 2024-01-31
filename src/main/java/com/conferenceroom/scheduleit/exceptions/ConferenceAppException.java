package com.conferenceroom.scheduleit.exceptions;

import com.conferenceroom.scheduleit.enums.ErrorCodesEnum;
import lombok.Getter;

@Getter
public class ConferenceAppException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    public ConferenceAppException(ErrorCodesEnum errorCodesEnum) {
        super();
        this.errorCode = errorCodesEnum.getErrorCode();
        this.errorMessage = errorCodesEnum.getErrorMessage();
    }
}