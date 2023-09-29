package dev.akash.productservicespring.exceptions;

import dev.akash.productservicespring.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler({NotFoundException.class})
    private ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage() ),HttpStatus.NOT_FOUND );
    }
}
