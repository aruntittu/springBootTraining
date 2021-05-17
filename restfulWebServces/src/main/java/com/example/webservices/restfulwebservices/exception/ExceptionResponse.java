package com.example.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String detail;

    ExceptionResponse(Date timeStamp, String message, String detail) {
        this.timestamp = timeStamp;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimeStamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
