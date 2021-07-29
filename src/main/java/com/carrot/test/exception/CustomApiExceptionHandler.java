package com.carrot.test.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomApiExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<CustomExceptionResponseDto> customException(CustomApiException customException){
        CustomExceptionResponseDto customExceptionResponseDto = new CustomExceptionResponseDto();
        customExceptionResponseDto.setErrorCode(customException.getErrorInfo());
        customExceptionResponseDto.setErrorMsg(customException.getErrorInfo());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customExceptionResponseDto);
    }


}