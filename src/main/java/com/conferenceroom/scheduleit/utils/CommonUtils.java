package com.conferenceroom.scheduleit.utils;

import com.conferenceroom.scheduleit.records.ApiSuccessResponse;

public class CommonUtils {
    private CommonUtils(){
    }
    public static ApiSuccessResponse<Object> buildResponse(Object data) {
        return new ApiSuccessResponse<>(true, data);
    }
}
