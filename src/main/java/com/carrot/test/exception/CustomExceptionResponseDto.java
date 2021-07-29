package com.carrot.test.exception;

import lombok.Data;

@Data
public class CustomExceptionResponseDto {
    private String errorCode;
    private String errorMsg;
}
