package com.student.firstproject.advice;

import com.student.firstproject.exception.StudentListEmptyException;
import com.student.firstproject.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidInput(MethodArgumentNotValidException ex) {
        Map<String, String> errMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errMap.put(error.getField(), error.getDefaultMessage());
        });
        return errMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public Map<String, String> handleStudentNotFound(StudentNotFoundException ex) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("Exception " , ex.getMessage());
        return errMap;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentListEmptyException.class)
    public Map<String, String> handleStudentNotFound(StudentListEmptyException ex) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("Exception " , ex.getMessage());
        return errMap;
    }
}
